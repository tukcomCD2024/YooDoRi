package kr.ac.tukorea.whereareu.presentation.nok.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoRequest
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoResponse
import kr.ac.tukorea.whereareu.data.repository.setting.SettingRepositoryImpl
import kr.ac.tukorea.whereareu.util.network.onSuccess
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    val repository: SettingRepositoryImpl
) : ViewModel() {
    private val _updateUserInfo = MutableSharedFlow<ModifyUserInfoResponse>()
    val updateUserInfo = _updateUserInfo.asSharedFlow()

    private val _updateOtherUserInfo = MutableSharedFlow<ModifyUserInfoResponse>()
    val updateOtherUserInfo = _updateOtherUserInfo.asSharedFlow()

    fun setUpdateUserInfo(request: ModifyUserInfoRequest){
        viewModelScope.launch(Dispatchers.IO){
            repository.sendModifyUserInfo(request).onSuccess {response ->
//                Log.d("UpdateUserInfo","UserInfoChanged")
//                _updateOtherUserInfo.emit(ModifyUserInfoResponse(it.result))
                Log.d("UpdateUserInfo", "UserInfoChanged")
                response?.let {
                    _updateOtherUserInfo.emit(ModifyUserInfoResponse(it.result))
                } ?: run {
                    Log.e("UpdateUserInfo", "ModifyUserInfoResponse is null")
                }
            }
        }
    }
    fun setUpdateOtherUserInfo(request: ModifyUserInfoRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sendModifyUserInfo(request).onSuccess {
                Log.d("UpdateOtherUserInfo", "OtherUserInfoChanged")
                _updateOtherUserInfo.emit(ModifyUserInfoResponse(it.result))
            }
        }
    }
}