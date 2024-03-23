package kr.ac.tukorea.whereareu.data.repository.setting

import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoRequest
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoResponse
import kr.ac.tukorea.whereareu.util.network.NetworkResult

interface SettingRepository {
    suspend fun sendModifyUserInfo(request:ModifyUserInfoRequest): NetworkResult<ModifyUserInfoResponse>
}