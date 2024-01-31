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
    //private val _nokIdentityResult = MutableLiveData<NokIdentityResponse>()
    //val nokIdentityResult: LiveData<NokIdentityResponse> get() = _nokIdentityResult

    private val _testError = MutableLiveData<String>()
    val testError: LiveData<String> get() = _testError

    private val _testSuccess = MutableLiveData<String>()
    val testSuccess: LiveData<String> get() = _testSuccess

    private val _dementiaOtp = MutableLiveData<String>()
    val dementiaOtp: LiveData<String> get() = _dementiaOtp

    private val _nokOtp = MutableLiveData<String>()
    val nokOtp: LiveData<String> get() = _nokOtp

    private val _isConnect = MutableLiveData<Boolean>()
    val isConnect: LiveData<Boolean> get() = _isConnect

    fun sendNokIdentity(otp: String, name: String, phoneNumber: String) {
        var response: NokIdentityResponse? = null
        viewModelScope.launch(Dispatchers.IO) {
            val result = handleApi({
                repository.sendNokIdentity(
                    NokIdentity(otp, name, phoneNumber)
                )
            }, { NokIdentityResponse -> NokIdentityResponse })
            withContext(Dispatchers.Main) {
                result.onSuccess {
                    //_nokIdentityResult.value = it
                    it.status
                    Log.d("NokIdentity Success", it.toString())
                    _testSuccess.value = "success"
                    response = it
                }
                result.onFail { _testError.value = "api 연동 실패" }
                result.onError { _testError.value = "api 연동 error: $it" }
                result.onException { _testError.value = "api 연동 exception: $it" }
            }
        }
    }


    fun sendDementiaIdentity(name: String, phoneNumber: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = handleApi({
            repository.sendDementiaIdentity(
                DementiaIdentity(name, phoneNumber)
            )
        }, { DementiaIdentityResponse -> DementiaIdentityResponse })
        withContext(Dispatchers.Main) {
            result.onSuccess {
                //_nokIdentityResult.value = it
                Log.d("DementiaIdentity Success", it.toString())
                _dementiaOtp.value = it.dementiaKey
                _testSuccess.value = "success"
            }
            result.onFail { _testError.value = "api 연동 실패" }
            result.onError { _testError.value = "api 연동 error: $it" }
            result.onException { _testError.value = "api 연동 exception: $it" }
        }
    }
}