package kr.ac.tukorea.whereareu.presentation.nok.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.data.model.DementiaKeyRequest
import kr.ac.tukorea.whereareu.data.repository.kakao.KakaoRepositoryImpl
import kr.ac.tukorea.whereareu.data.repository.naver.NaverRepositoryImpl
import kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl
import kr.ac.tukorea.whereareu.domain.home.LastLocation
import kr.ac.tukorea.whereareu.domain.home.MeaningfulPlaceInfo
import kr.ac.tukorea.whereareu.domain.home.DementiaStatusInfo
import kr.ac.tukorea.whereareu.domain.home.LocationInfo
import kr.ac.tukorea.whereareu.domain.home.PoliceStationInfo
import kr.ac.tukorea.whereareu.domain.home.PredictLocation
import kr.ac.tukorea.whereareu.util.network.onError
import kr.ac.tukorea.whereareu.util.network.onException
import kr.ac.tukorea.whereareu.util.network.onFail
import kr.ac.tukorea.whereareu.util.network.onSuccess
import javax.inject.Inject
import kotlin.system.measureTimeMillis

@HiltViewModel
class NokHomeViewModel @Inject constructor(
    private val nokHomeRepository: NokHomeRepositoryImpl,
    private val naverRepository: NaverRepositoryImpl,
    private val kakaoRepository: KakaoRepositoryImpl
) : ViewModel() {
    private var tag = "HomeViewModel:"

    private val _dementiaLocationInfo = MutableSharedFlow<LocationInfo>()
    val dementiaLocationInfo = _dementiaLocationInfo.asSharedFlow()

    val dementiaStatusInfo = MutableStateFlow(DementiaStatusInfo())

    private val _updateRate = MutableSharedFlow<Long>()
    val updateRate = _updateRate.asSharedFlow()

    private val _isPredicted = MutableStateFlow(false)
    val isPredicted = _isPredicted.asStateFlow()

    private val _isPredictDone = MutableStateFlow(false)

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
    }

    sealed interface NavigateEvent {
        data object Home : NavigateEvent
        data object Setting : NavigateEvent
        data object MeaningfulPlace : NavigateEvent
        data object LocationHistory : NavigateEvent
        data object SafeArea : NavigateEvent
        data class HomeState(val isPredicted: Boolean, val isPredictDone: Boolean) : NavigateEvent
    }

    fun eventNavigate(event: NavigateEvent) {
        viewModelScope.launch {
            if ((event is NavigateEvent.HomeState).not()) {
                navigateEventToString.value = event.toString()
            }
            _navigateEvent.emit(event)
        }
    }

    fun eventPredict(event: PredictEvent) {
        viewModelScope.launch {
            _predictEvent.emit(event)
        }
    }

    fun setIsPredictDone(isPredictDone: Boolean) {
        _isPredictDone.value = isPredictDone
    }

    fun eventHomeState() {
        viewModelScope.launch {
            eventNavigate(NavigateEvent.HomeState(_isPredicted.value, _isPredictDone.value))
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

    fun eventPredictLocation(){
        viewModelScope.launch {
            if(tempPredictLocation.value == PredictLocation() || !_isPredicted.value){
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

    fun setIsPredicted(isPredicted: Boolean) {
        viewModelScope.launch {
            _isPredicted.emit(isPredicted)
            if (isPredicted) {
                eventPredict(PredictEvent.StartPredict(isPredicted))
            } else {
                eventPredict(PredictEvent.StopPredict(isPredicted))
            }
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
                if (_isPredicted.value) {
                    return@launch
                }
                _dementiaLocationInfo.emit(it.toModel(_isPredicted.value))
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
            }
            Log.d("after refactor time", time.toString())
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
}