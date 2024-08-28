package kr.ac.tukorea.whereareu.presentation.nok.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.data.model.DementiaKeyRequest
import kr.ac.tukorea.whereareu.data.model.kakao.address.AddressResponse
import kr.ac.tukorea.whereareu.data.model.nok.home.LocationInfoResponse
import kr.ac.tukorea.whereareu.data.model.nok.home.SafeArea
import kr.ac.tukorea.whereareu.data.model.nok.safearea.SafeAreaGroup
import kr.ac.tukorea.whereareu.data.repository.kakao.KakaoRepositoryImpl
import kr.ac.tukorea.whereareu.data.repository.naver.NaverRepositoryImpl
import kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl
import kr.ac.tukorea.whereareu.data.repository.nok.safearea.SafeAreaRepository
import kr.ac.tukorea.whereareu.data.repository.nok.safearea.SafeAreaRepositoryImpl
import kr.ac.tukorea.whereareu.domain.home.LastLocation
import kr.ac.tukorea.whereareu.domain.home.MeaningfulPlaceInfo
import kr.ac.tukorea.whereareu.domain.home.DementiaStatusInfo
import kr.ac.tukorea.whereareu.domain.home.PoliceStationInfo
import kr.ac.tukorea.whereareu.domain.home.PredictLocation
import kr.ac.tukorea.whereareu.presentation.nok.safearea.SafeAreaViewModel
import kr.ac.tukorea.whereareu.util.network.onError
import kr.ac.tukorea.whereareu.util.network.onException
import kr.ac.tukorea.whereareu.util.network.onFail
import kr.ac.tukorea.whereareu.util.network.onSuccess
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class NokHomeViewModel @Inject constructor(
    private val nokHomeRepository: NokHomeRepositoryImpl,
    private val safeAreaRepository: SafeAreaRepositoryImpl,
    private val naverRepository: NaverRepositoryImpl,
    private val kakaoRepository: KakaoRepositoryImpl,
) : ViewModel() {
    private var tag = "HomeViewModel:"

    private val _dementiaLocationInfo = MutableSharedFlow<LocationInfoResponse>()
    val dementiaLocationInfo = _dementiaLocationInfo.asSharedFlow()

    val dementiaStatusInfo = MutableStateFlow(DementiaStatusInfo())

    private val _updateRate = MutableSharedFlow<Long>()
    val updateRate = _updateRate.asSharedFlow()

    val isPredicted = MutableStateFlow(false)

    private val _isPredictDone = MutableStateFlow(false)

    val isSos = MutableStateFlow(false)
    private val _isSosDone = MutableStateFlow(false)
    private val _sosEvent = MutableSharedFlow<SosEvent>()
    val sosEvent = _sosEvent.asSharedFlow()
    val sosTimerValue = MutableStateFlow(30)
    private var sosJob: Job? = null


    private val _dementiaKey = MutableStateFlow("")
    private val _nokKey = MutableStateFlow("")

    private val _predictEvent = MutableSharedFlow<PredictEvent>()
    val predictEvent = _predictEvent.asSharedFlow()

    private val _dementiaName = MutableStateFlow("")
    val dementiaName = _dementiaName.asStateFlow()

    private val _navigateEvent = MutableSharedFlow<NavigateEvent>()
    val navigateEvent = _navigateEvent.asSharedFlow()

    val navigateEventToString = MutableStateFlow(NavigateEvent.Home.toString())

    private val _tempMeaningfulPlace = MutableStateFlow<List<MeaningfulPlaceInfo>>(emptyList())

    val tempPredictLocation = MutableStateFlow<PredictLocation>(PredictLocation())

    private val _meaningfulPlace = MutableSharedFlow<List<MeaningfulPlaceInfo>>()
    val meaningfulPlace = _meaningfulPlace.asSharedFlow()

    sealed class SosEvent {
        data class StartSos(val isSos: Boolean) : SosEvent()

        data object SosDone : SosEvent()

        data class StopSos(val isSos: Boolean) : SosEvent()
    }

    sealed class PredictEvent {
        data class StartPredict(val isPredicted: Boolean) : PredictEvent()
        data class MeaningFulPlace(
            val meaningfulPlaceForList: List<MeaningfulPlaceInfo>
        ) : PredictEvent()

        data class PredictLocation(
            val predictLocation: kr.ac.tukorea.whereareu.domain.home.PredictLocation
        ) : PredictEvent()

        data class DisplayDementiaLastInfo(val averageSpeed: Double, val coord: LatLng) :
            PredictEvent()

        data class DisplayDementiaLastLocation(val lastLocation: LastLocation) : PredictEvent()

        data class SearchNearbyPoliceStation(val policeStationList: List<PoliceStationInfo>) :
            PredictEvent()

        data object PredictDone : PredictEvent()

        data class MapView(val behavior: Int, val coord: LatLng) : PredictEvent()

        data class StopPredict(val isPredicted: Boolean) : PredictEvent()

        data class FetchSafeArea(val groupList: List<SafeArea>) : PredictEvent()
    }

    private val userMeaningfulPlace = mutableListOf<MeaningfulPlaceInfo>()

    sealed interface NavigateEvent {
        data object Home : NavigateEvent
        data object Setting : NavigateEvent
        data object MeaningfulPlace : NavigateEvent
        data object LocationHistory : NavigateEvent
        data object SafeArea : NavigateEvent

        data object SafeAreaDetail : NavigateEvent

        data object SafeAreaSetting : NavigateEvent
        data class HomeState(val isPredicted: Boolean, val isPredictDone: Boolean) : NavigateEvent
    }

    fun eventNavigate(event: NavigateEvent) {
        viewModelScope.launch {
            if (event !is NavigateEvent.HomeState) {
                navigateEventToString.value = event.toString()
            }
            if (event !is NavigateEvent.Home) {
                _navigateEvent.emit(event)
            }
        }
    }

    fun eventPredict(event: PredictEvent) {
        viewModelScope.launch {
            _predictEvent.emit(event)
        }
    }

    fun eventSos(event: SosEvent) {
        viewModelScope.launch {
            _sosEvent.emit(event)
        }
    }

    fun eventHomeState(
        isPredicted: Boolean = this.isPredicted.value,
        isPredictDone: Boolean = _isPredictDone.value,
        isSos: Boolean = this.isSos.value,
        isSosDone: Boolean = _isSosDone.value
    ) {
        this.isPredicted.value = isPredicted
        _isPredictDone.value = isPredictDone
        this.isSos.value = isSos
        _isSosDone.value = isSosDone

        viewModelScope.launch {
            eventNavigate(NavigateEvent.HomeState(isPredicted, isPredictDone))
            if (isPredicted) {
                if (!isPredictDone) {
                    eventPredict(PredictEvent.StartPredict(true))
                }
            } else {
                eventPredict(PredictEvent.StopPredict(false))
            }
            if (isSos) {
                if (!isSosDone) {
                    eventSos(SosEvent.StartSos(true))
                }
            } else {
                eventSos(SosEvent.StopSos(false))
            }
        }
    }

    fun eventMeaningfulPlace() {
        viewModelScope.launch {
            if (_tempMeaningfulPlace.value.isEmpty()) {
                Log.d("$tag eventMeaningfulPlace", "_meaningfulPlace isEmpty")
                return@launch
            }
            _meaningfulPlace.emit(_tempMeaningfulPlace.value)
        }
    }

    fun eventPredictLocation() {
        viewModelScope.launch {
            if (tempPredictLocation.value == PredictLocation() || !isPredicted.value) {
                return@launch
            }
            eventPredict(PredictEvent.PredictLocation(tempPredictLocation.value))
        }
    }

    fun setDementiaKey(dementiaKey: String) {
        _dementiaKey.value = dementiaKey
    }

    fun setNokKey(nokKey: String) {
        _nokKey.value = nokKey
    }

    fun setUpdateRate(updateRate: Long) {
        viewModelScope.launch {
            _updateRate.emit(updateRate)
        }
    }

    fun fetchUserInfo() {
        viewModelScope.launch {
            nokHomeRepository.getUserInfo(_nokKey.value).onSuccess {
                _dementiaName.emit(it.dementiaInfoRecord.dementiaName)
                _updateRate.emit(it.nokInfoRecord.updateRate.toLong())
                Log.d("$tag fetchUserInfo", it.toString())
            }
        }
    }

    fun getDementiaLocation() {
        viewModelScope.launch {
            nokHomeRepository.getDementiaLocationInfo(_dementiaKey.value).onSuccess {
                if (isPredicted.value) {
                    return@launch
                }
                _dementiaLocationInfo.emit(it)
                dementiaStatusInfo.value = DementiaStatusInfo(
                    it.userStatus, it.battery, it.isGpsOn, it.isInternetOn, it.isRingstoneOn
                )

                Log.d("$tag getDementiaLocation", it.toString())
            }.onError {
                Log.d("$tag error", it.toString())
            }.onException {
                Log.d("$tag exception", it.toString())
            }.onFail {
                Log.d("$tag fail", it.toString())
            }
        }
    }

    fun predict() {
        viewModelScope.launch {
            val time = measureTimeMillis {
                async { getDementiaLastInfo() }
                async { getMeaningfulPlaces() }
                async { fetchPredictInfoGura() }.await()
                eventPredict(PredictEvent.PredictDone)
                eventHomeState(isPredicted = true, isPredictDone = true)
            }
            Log.d("after refactor time", time.toString())
        }
    }

    fun sos() {
        viewModelScope.launch {
            isSos.value = true
            eventSos(SosEvent.StartSos(true))
            eventHomeState(isSos = true, isSosDone = true)
            startSosTimer()
        }
    }

    fun startSosTimer() {
        sosJob?.cancel()
        sosJob = viewModelScope.launch {
            for (i in 30 downTo 0) {
                sosTimerValue.emit(i)
                delay(1000L)
            }
            eventSos(SosEvent.SosDone) // SOS가 완료되면 알림
            isSos.value = false
        }
    }

    fun cancelSosTimer() {
        sosJob?.cancel()
        sosJob = null
        viewModelScope.launch {
            sosTimerValue.emit(30)  // 타이머 값을 초기화
            // UI 상태를 리셋하여 초기 화면으로 돌아가도록 처리
            isSos.value = false  // SOS 상태를 false로 변경
            _sosEvent.emit(SosEvent.StopSos(false)) // SOS 종료 이벤트 전송
        }
    }


    private suspend fun getDementiaLastInfo() {
        nokHomeRepository.getDementiaLastInfo(DementiaKeyRequest(_dementiaKey.value))
            .onSuccess { response ->
                Log.d("$tag getDementiaLastInfo", response.toString())
                val averageSpeed = response.averageSpeed.div(3.6)
                val latLng = LatLng(response.lastLatitude, response.lastLongitude)

                eventPredict(PredictEvent.DisplayDementiaLastInfo(averageSpeed, latLng))

                eventPredict(
                    PredictEvent.DisplayDementiaLastLocation(
                        LastLocation(latLng, response.addressName)
                    )
                )
            }.onException {
                Log.d("$tag error", it.toString())
            }
    }

    private suspend fun getMeaningfulPlaces() {
        nokHomeRepository.getMeaningfulPlace(_dementiaKey.value).onSuccess { response ->
            Log.d("$tag getMeaningfulPlaces", response.toString())
            val meaningfulPlaceInfo = response.meaningfulPlaces.map { meaningfulPlace ->
                val policeStationInfo = meaningfulPlace.policeStationInfo.map { policeStation ->
                    policeStation.toModel()
                }
                eventPredict(PredictEvent.SearchNearbyPoliceStation(policeStationInfo))
                meaningfulPlace.toModel(policeStationInfo)
            }

            eventPredict(PredictEvent.MeaningFulPlace(meaningfulPlaceInfo))
            _tempMeaningfulPlace.value = meaningfulPlaceInfo
            _meaningfulPlace.emit(meaningfulPlaceInfo)
        }.onException {
            Log.d("$tag error", it.toString())
        }
    }

    private suspend fun fetchPredictInfo() {
        nokHomeRepository.fetchPredictInfo(_dementiaKey.value).onSuccess { response ->
            Log.d("$tag fetchPredictInfo", response.toString())
            with(response) {
                val policeStationInfo = policeInfo.map { it.toModel() }
                val meaningfulPlace = MeaningfulPlaceInfo(
                    predictLocation.address,
                    emptyList(),
                    LatLng(
                        predictLocation.latitude.toDouble(),
                        predictLocation.longitude.toDouble()
                    ),
                    false,
                    policeStationInfo
                )
                val predictLocation = PredictLocation(meaningfulPlace, policeStationInfo)
                eventPredict(PredictEvent.PredictLocation(predictLocation))
                tempPredictLocation.value = predictLocation
            }
        }.onException {
            Log.d("predict exception", it.toString())
        }
    }

    private suspend fun fetchPredictInfoGura() {
        nokHomeRepository.fetchPredictInfoGura(_dementiaKey.value).onSuccess { response ->
            Log.d("$tag fetchPredictInfoGura", response.toString())
            with(response) {
                val policeStationInfo = policeInfo.map { it.toModel() }
                val meaningfulPlace = MeaningfulPlaceInfo(
                    predictLocation.address,
                    emptyList(),
                    LatLng(
                        predictLocation.latitude.toDouble(),
                        predictLocation.longitude.toDouble()
                    ),
                    false,
                    policeStationInfo
                )
                val predictLocation = PredictLocation(meaningfulPlace, policeStationInfo)
                eventPredict(PredictEvent.PredictLocation(predictLocation))
                tempPredictLocation.value = predictLocation
            }
        }.onException {
            Log.d("predict exception", it.toString())
        }
    }

    fun fetchSafeAreaAll() {
        viewModelScope.launch {
            safeAreaRepository.fetchSafeAreaInfoAll(_dementiaKey.value).onSuccess { response ->
                if (response.safeAreas.isEmpty()) {
                    return@launch
                }
                eventPredict(PredictEvent.FetchSafeArea(response.safeAreas))

                //val safeAreaList = mutableListOf<SafeArea>()
                //val groupNameList = response.safeAreaList.map { it.groupName}.filterNot { it == "notGrouped" }

                /*response.safeAreaList.forEach { _safeAreaList ->
                    val temp = if (_safeAreaList.groupName == "notGrouped") {
                        _safeAreaList.safeAreas.map { safeArea ->
                            SafeArea(
                                "",
                                _safeAreaList.groupKey,
                                safeArea.areaKey,
                                safeArea.areaName,
                                safeArea.latitude,
                                safeArea.longitude,
                                safeArea.radius,
                                SafeAreaRVA.SAFE_AREA
                            )
                        }
                    } else {
                        _safeAreaList.safeAreas.map {
                            SafeArea(
                                _safeAreaList.groupName,
                                _safeAreaList.groupKey,
                                "",
                                "",
                                0.0,
                                0.0,
                                0,
                                SafeAreaRVA.SAFE_AREA_GROUP
                            )
                        }
                    }
                    safeAreaList.addAll(temp)
                }
                safeAreaList.sortWith(
                    compareBy(
                        {it.viewType},
                        {it.groupName},
                        {it.areaName}
                    )
                )*/
                //savedStateHandle["safeAreaGroupList"] = groupList
                //Log.d("safeArea List", safeAreaList.toString())
                Log.d("fetchSafeArea", response.toString())
            }
        }
    }
}