package kr.ac.tukorea.whereareu.data.api

import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.data.model.home.PostLocationInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DementiaHomeService {
    @POST("receive-location-info")
    suspend fun postLocationInfo(
        @Body request: LocationInfo
    ): Response<PostLocationInfoResponse>

}