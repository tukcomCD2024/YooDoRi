package kr.ac.tukorea.whereareu.data.api

import kr.ac.tukorea.whereareu.data.model.login.CheckConnect
import kr.ac.tukorea.whereareu.data.model.login.CheckConnectedResponse
import kr.ac.tukorea.whereareu.data.model.login.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.login.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.data.model.login.NokIdentity
import kr.ac.tukorea.whereareu.data.model.login.NokIdentityResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("receive-nok-info")
    suspend fun postNokIdentity(@Body request: NokIdentity): Response<NokIdentityResponse>

    @POST("receive-dementia-info")
    suspend fun postDementiaIdentity(@Body request: DementiaIdentity): Response<DementiaIdentityResponse>

    @POST("is-connected")
    suspend fun postIsConnected(@Body request: CheckConnect): Response<CheckConnectedResponse>
}