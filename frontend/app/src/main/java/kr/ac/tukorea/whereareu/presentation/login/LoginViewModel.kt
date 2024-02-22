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
import kr.ac.tukorea.whereareu.data.model.login.CheckConnect
import kr.ac.tukorea.whereareu.data.model.login.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.login.NokIdentity
import kr.ac.tukorea.whereareu.data.repository.LoginRepositoryImpl
import kr.ac.tukorea.whereareu.util.onSuccess
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepositoryImpl
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<Event>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _dementiaKeyFlow = MutableStateFlow("000000")
    val dementiaKeyFlow = _dementiaKeyFlow.asStateFlow()

    private val _dementiaNameFlow = MutableStateFlow("사용자")
    val dementiaNameFlow = _dementiaNameFlow.asStateFlow()

    private val _dementiaPhoneFlow = MutableStateFlow("010-0000-0000")
    val dementiaPhoneFlow = _dementiaPhoneFlow.asStateFlow()

    private val _dementiaIdentityFlow = MutableStateFlow(DementiaIdentity())
    val dementiaIdentityFlow = _dementiaIdentityFlow.asStateFlow()

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

    private fun isSuccess(result: String, event: Event) {
        event(if (result == "success") event else Event.Fail)
    }

    fun sendNokIdentity(request: NokIdentity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendNokIdentity(request).onSuccess {
                isSuccess(it.status, Event.NavigateToMain)

                _dementiaIdentityFlow.emit(DementiaIdentity(it.dementiaInfo.dementiaName, it.dementiaInfo.dementiaPhonenumber))
            }
        }
    }

//    fun getDementiaName(): String {
//        Log.d("doyoung", "getDementiaName : ${dementiaNameFlow.value}")
//        return dementiaNameFlow.value
//        return dementiaNameFlow.value
//    }
//    fun getDementiaPhone() : String {
//        return dementiaPhoneFlow.value
//        return dementiaPhoneFlow.value?.dementiaInfo?.dementiaName
//    }

    fun sendDementiaIdentity(request: DementiaIdentity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendDementiaIdentity(request).onSuccess {
                isSuccess(it.status, Event.NavigateToPatientOtp)
                _dementiaKeyFlow.emit(it.dementiaKey)
            }
        }
    }

    fun checkConnected(request: CheckConnect) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkInterConnected(request).onSuccess {
                isSuccess(it.status, Event.NavigateToMain)
                Log.d("되나", it.toString())
            }
        }
    }

}