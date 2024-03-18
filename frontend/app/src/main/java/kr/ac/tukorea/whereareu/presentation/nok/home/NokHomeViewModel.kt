package kr.ac.tukorea.whereareu.presentation.nok.home

import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse
import kr.ac.tukorea.whereareu.data.repository.nok.home.NokHomeRepositoryImpl
import kr.ac.tukorea.whereareu.util.network.onError
import kr.ac.tukorea.whereareu.util.network.onException
import kr.ac.tukorea.whereareu.util.network.onFail
import kr.ac.tukorea.whereareu.util.network.onSuccess
import javax.inject.Inject

@HiltViewModel
class NokHomeViewModel @Inject constructor(
    val repository: NokHomeRepositoryImpl
): ViewModel() {

    private val _dementiaLocation = MutableSharedFlow<GetLocationInfoResponse>(replay = 1)
    val dementiaLocation = _dementiaLocation.asSharedFlow()

    private val _updateDuration = MutableStateFlow<Long>(60*1000)
    val updateDuration = _updateDuration.asStateFlow()

    private val _userPhoneNumber = MutableStateFlow<ContactsContract.CommonDataKinds.Phone>()

    fun setUpdateDuration(duration: Long){
        viewModelScope.launch {
            //Log.d("duration", duration.toString())
            _updateDuration.emit(duration*60*1000)
        }
    }
    fun setUserPhoneNumber(number: Number){
        viewModelScope.launch {

        }
    }
    fun getDementiaLocation(dementiaKey: String){
        viewModelScope.launch {
            repository.getDementiaLocationInfo(dementiaKey).onSuccess {
                _dementiaLocation.emit(it)
            }.onError {
                Log.d("error", it.toString())
            }.onException {
                Log.d("exception", it.toString())
            }.onFail {
                Log.d("fail", it.toString())
            }
        }
    }
}