package kr.ac.tukorea.whereareu.data.api

import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfo
import retrofit2.http.Body
import retrofit2.http.POST

interface ModifyUserInfoService {
    @POST("modify-user-info")
    suspend fun postModifyUserInfo(@Body request : ModifyUserInfo)
}