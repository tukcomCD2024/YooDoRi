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
import kotlinx.coroutines.withContext
import kr.ac.tukorea.whereareu.data.model.CheckConnect
import kr.ac.tukorea.whereareu.data.model.CheckConnectNokInfoRecord
import kr.ac.tukorea.whereareu.data.model.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.NokIdentity
import kr.ac.tukorea.whereareu.data.model.NokIdentityResponse
import kr.ac.tukorea.whereareu.data.repository.LoginRepositoryImpl
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

    private val _dementiaKeyFlow = MutableStateFlow("000000")
    val dementiaKeyFlow = _dementiaKeyFlow.asStateFlow()

    private val _dementiaIdentityFlow = MutableStateFlow(DementiaIdentity())
    val dementiaIdentityFlow = _dementiaIdentityFlow.asStateFlow()

    private val _nokIdentityFlow = MutableStateFlow(NokIdentity())
    val nokIdentityFlow = _nokIdentityFlow.asStateFlow()


    private fun event(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
            Log.d("가냐", "_eventFlow")
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

                _dementiaIdentityFlow.emit(DementiaIdentity(it.result.dementiaInfoRecord.dementiaName, it.result.dementiaInfoRecord.dementiaPhoneNumber))
            }
        }
    }

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
                _nokIdentityFlow.emit(NokIdentity(it.result.nokInfoRecord.nokKey, it.result.nokInfoRecord.nokName, it.result.nokInfoRecord.nokPhoneNumber))
                Log.d("되나", it.toString())
            }
        }
    }

}