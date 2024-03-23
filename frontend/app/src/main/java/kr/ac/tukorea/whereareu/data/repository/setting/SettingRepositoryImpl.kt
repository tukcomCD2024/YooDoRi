package kr.ac.tukorea.whereareu.data.repository.setting

import kr.ac.tukorea.whereareu.data.api.ModifyUserInfoService
import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoRequest
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoResponse
import kr.ac.tukorea.whereareu.util.network.NetworkResult
import kr.ac.tukorea.whereareu.util.network.handleApi
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val api: ModifyUserInfoService
) : SettingRepository{
    override suspend fun sendModifyUserInfo(request: ModifyUserInfoRequest): NetworkResult<ModifyUserInfoResponse> {
        return handleApi({api.postModifyUserInfo(request)}) {response : ResponseBody<ModifyUserInfoResponse> -> response.result}
    }
}