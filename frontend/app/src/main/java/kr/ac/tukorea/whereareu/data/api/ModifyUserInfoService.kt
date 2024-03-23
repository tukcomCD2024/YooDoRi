package kr.ac.tukorea.whereareu.data.api

import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoRequest
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body

interface ModifyUserInfoService {
    @POST("modify-user-info")
    suspend fun postModifyUserInfo(@Body request : ModifyUserInfoRequest): Response<ResponseBody<ModifyUserInfoResponse>>
}