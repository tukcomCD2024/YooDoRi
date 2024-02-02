package kr.ac.tukorea.whereareu.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kr.ac.tukorea.whereareu.data.model.CheckConnect
import kr.ac.tukorea.whereareu.data.model.CheckConnectedResponse
import kr.ac.tukorea.whereareu.data.model.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.data.model.NokIdentity
import kr.ac.tukorea.whereareu.data.model.NokIdentityResponse
import kr.ac.tukorea.whereareu.data.repository.LoginRepositoryImpl
import kr.ac.tukorea.whereareu.util.NetworkResult
import kr.ac.tukorea.whereareu.util.handleApi
import kr.ac.tukorea.whereareu.util.onError
import kr.ac.tukorea.whereareu.util.onException
import kr.ac.tukorea.whereareu.util.onFail
import kr.ac.tukorea.whereareu.util.onSuccess
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepositoryImpl
) : ViewModel() {

    private val _apiError = MutableLiveData<String>()
    val apiError: LiveData<String> get() = _apiError

    private val _apiSuccess = MutableLiveData<String>()
    val apiSuccess: LiveData<String> get() = _apiSuccess

    private val _dementiaOtp = MutableLiveData<String>()
    val dementiaOtp: LiveData<String> get() = _dementiaOtp

    private val _nokOtp = MutableLiveData<String>()
    val nokOtp: LiveData<String> get() = _nokOtp

    private val _isConnect = MutableLiveData<String>()
    val isConnect: LiveData<String> get() = _isConnect

    fun resetApiSuccess(reset: String){
        _apiSuccess.value = reset
    }

    fun sendNokIdentity(otp: String, name: String, phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = handleApi({
                repository.sendNokIdentity(
                    NokIdentity(otp, name, phoneNumber)
                )
            }, { NokIdentityResponse -> NokIdentityResponse })
            withContext(Dispatchers.Main) {
                result.onSuccess {
                    Log.d("NokIdentity Success", it.toString())
                    _apiSuccess.value = it.status
                }
                result.onFail { _apiError.value = "api 연동 실패" }
                result.onError { _apiError.value = "api 연동 error: $it" }
                result.onException { _apiError.value = "api 연동 exception: $it" }
            }
        }
    }


    fun sendDementiaIdentity(name: String, phoneNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = handleApi({
                repository.sendDementiaIdentity(
                    DementiaIdentity(name, phoneNumber)
                )
            }, { DementiaIdentityResponse -> DementiaIdentityResponse })
            withContext(Dispatchers.Main) {
                result.onSuccess {
                    Log.d("DementiaIdentity Success", it.toString())
                    _dementiaOtp.value = it.dementiaKey
                    _apiSuccess.value = it.status
                }
                result.onFail { _apiError.value = "api 연동 실패" }
                result.onError { _apiError.value = "api 연동 error: $it" }
                result.onException { _apiError.value = "api 연동 exception: $it" }
            }
        }
    }

    fun checkConnected(otp: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = handleApi({
                repository.checkConnected(CheckConnect(otp))
            }, { CheckConnectedResponse -> CheckConnectedResponse })
            withContext(Dispatchers.Main) {
                result.onSuccess {
                    Log.d("Connected Success", it.toString())
                    _isConnect.value = it.status
                }
                result.onFail { _apiError.value = "api 연동 실패" }
                result.onError { _apiError.value = "api 연동 error: $it" }
                result.onException { _apiError.value = "api 연동 exception: $it" }
            }
        }
    }

}