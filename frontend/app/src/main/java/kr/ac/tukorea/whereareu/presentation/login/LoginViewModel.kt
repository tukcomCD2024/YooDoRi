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
import kr.ac.tukorea.whereareu.domain.login.NokInfo
import kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest
import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest
import kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse
import kr.ac.tukorea.whereareu.data.repository.login.LoginRepositoryImpl
import kr.ac.tukorea.whereareu.util.network.onError
import kr.ac.tukorea.whereareu.util.network.onException
import kr.ac.tukorea.whereareu.util.network.onFail
import kr.ac.tukorea.whereareu.util.network.onSuccess
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepositoryImpl
) : ViewModel() {

    private val _dementiaKeyFlow = MutableSharedFlow<String>(replay = 1)
    val dementiaKeyFlow = _dementiaKeyFlow.asSharedFlow()

    private val _navigateToNokMainEvent = MutableSharedFlow<NokIdentityResponse>()
    val navigateToNokMainEvent = _navigateToNokMainEvent.asSharedFlow()

    private val _navigateToDementiaMainEvent = MutableSharedFlow<NokInfo>()
    val navigateToDementiaMainEvent = _navigateToDementiaMainEvent.asSharedFlow()

    private val _isOnBackPressedAtDementiaOtp = MutableStateFlow(false)
    val isOnBackPressedAtDementiaOtp = _isOnBackPressedAtDementiaOtp.asStateFlow()

    private val _toastEvent = MutableSharedFlow<String>()
    val toastEvent = _toastEvent.asSharedFlow()

    fun onBackPressedAtDementiaOtp(isPressed: Boolean){
        viewModelScope.launch {
            _isOnBackPressedAtDementiaOtp.emit(isPressed)
        }
    }

    fun sendNokIdentity(request: NokIdentityRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendNokIdentity(request).onSuccess {
                _navigateToNokMainEvent.emit(it)
            }.onError {
                Log.d("error", it.toString())
            }.onException {
                Log.d("exception", it.toString())
            }.onFail {
                _toastEvent.emit("올바른 인증번호를 입력해주세요.")
            }
        }
    }

    fun sendDementiaIdentity(request: DementiaIdentityRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendDementiaIdentity(request).onSuccess {
                _dementiaKeyFlow.emit(it.dementiaKey)
            }
        }
    }

    fun checkConnected(request: CheckInterConnectRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.checkInterConnected(request).onSuccess {
                _navigateToDementiaMainEvent.emit(NokInfo(it.nokInfoRecord.nokKey, it.nokInfoRecord.nokName, it.nokInfoRecord.nokPhoneNumber))
                Log.d("되나", it.toString())
            }.onError {
                Log.d("send demenita", it.toString())
            }.onException {
                Log.d("excetion", it.toString())
            }.onFail {
                _toastEvent.emit("보호자의 연결 상태를 확인해주세요.")
            }
        }
    }

}