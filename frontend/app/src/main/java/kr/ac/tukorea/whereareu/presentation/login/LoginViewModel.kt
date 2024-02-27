package kr.ac.tukorea.whereareu.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest
import kr.ac.tukorea.whereareu.domain.login.CheckConnectNokInfoRecord
import kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest
import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest
import kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse
import kr.ac.tukorea.whereareu.data.repository.LoginRepositoryImpl
import kr.ac.tukorea.whereareu.domain.login.DementiaInfo
import kr.ac.tukorea.whereareu.util.onError
import kr.ac.tukorea.whereareu.util.onException
import kr.ac.tukorea.whereareu.util.onFail
import kr.ac.tukorea.whereareu.util.onSuccess
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepositoryImpl
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _dementiaKeyFlow = MutableSharedFlow<String>(replay = 1)
    val dementiaKeyFlow = _dementiaKeyFlow.asSharedFlow()

    private val _nokKeyFlow = MutableSharedFlow<String>()
    val nokKeyFlow = _nokKeyFlow.asSharedFlow()

    private val _navigateToNokMainEvent = MutableSharedFlow<NokIdentityResponse>()
    val navigateToNokMainEvent = _navigateToNokMainEvent.asSharedFlow()

    /*private val _dementiaIdentityFlow = MutableStateFlow(DementiaIdentity())
    val dementiaIdentityFlow = _dementiaIdentityFlow.asStateFlow()*/

    /*private val _nokIdentityFlow = MutableStateFlow(NokIdentity())
    val nokIdentityFlow = _nokIdentityFlow.asStateFlow()*/

    private val _nokIdentityFlow = MutableSharedFlow<CheckConnectNokInfoRecord>()
    val nokIdentityFlow = _nokIdentityFlow.asSharedFlow()

    private val _dementiaIdentityEvent = MutableSharedFlow<Boolean>()
    val dementiaIdentityEvent = _dementiaIdentityEvent.asSharedFlow()


    private val _isOnBackPressedAtDementiaOtp = MutableStateFlow(false)
    val isOnBackPressedAtDementiaOtp = _isOnBackPressedAtDementiaOtp.asStateFlow()

    fun onBackPressedAtDementiaOtp(isPressed: Boolean){
        viewModelScope.launch {
            _isOnBackPressedAtDementiaOtp.emit(isPressed)
        }
    }
    fun resetDementiaKey(){
        viewModelScope.launch {
            _dementiaKeyFlow.emit("000000")
        }
    }


    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    sealed class Event {
        data object NavigateToMain : Event()
        data object NavigateToPatientOtp : Event()
        data object Fail : Event()
    }

    /*private fun isSuccess(result: String, event: Event) {
        event(if (result == "success") event else Event.Fail)
    }*/

    fun sendNokIdentity(request: NokIdentityRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendNokIdentity(request).onSuccess {
                //isSuccess(it., Event.NavigateToMain)
                event(Event.NavigateToMain)
                _nokKeyFlow.emit(it.nokKey)
                _navigateToNokMainEvent.emit(it)
            }.onError {
                Log.d("error", it.toString())
            }.onException {
                Log.d("exception", it.toString())
            }.onFail {
                Log.d("fail", it.toString())
            }
        }
    }

    fun sendDementiaIdentity(request: DementiaIdentityRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendDementiaIdentity(request).onSuccess {
                //isSuccess(it.status, Event.NavigateToPatientOtp)
                //event(Event.NavigateToPatientOtp)
                _dementiaKeyFlow.emit(it.dementiaKey)
            }.onError {
                Log.d("send demenita", it.toString())
            }.onException {
                Log.d("excetion", it.toString())
            }.onFail {
                Log.d("fail", it.toString())
            }
        }
    }

    fun checkConnected(request: CheckInterConnectRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkInterConnected(request).onSuccess {
                //isSuccess(it.status, Event.NavigateToMain)
                event(Event.NavigateToMain)
                _nokIdentityFlow.emit(CheckConnectNokInfoRecord(it.nokInfoRecord.nokKey, it.nokInfoRecord.nokName, it.nokInfoRecord.nokPhoneNumber))
                Log.d("되나", it.toString())
            }
        }
    }

}