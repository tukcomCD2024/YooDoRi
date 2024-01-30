package kr.ac.tukorea.whereareu.data.api

import kr.ac.tukorea.whereareu.data.model.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.data.model.NokIdentity
import kr.ac.tukorea.whereareu.data.model.NokIdentityResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("receive-nok-info")
    suspend fun postNokIdentity(@Body request: NokIdentity): Response<NokIdentityResponse>

    @POST("receive-dementia-info")
    suspend fun postDementiaIdentity(@Body request: DementiaIdentity): Response<DementiaIdentityResponse>
}