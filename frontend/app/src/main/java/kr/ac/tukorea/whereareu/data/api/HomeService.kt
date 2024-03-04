package kr.ac.tukorea.whereareu.data.api

import kr.ac.tukorea.whereareu.data.model.LocationInfo
import kr.ac.tukorea.whereareu.data.model.LocationInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface HomeService {
    @POST("receive-location-info")
    suspend fun postLocationInfo(
        @Body request: LocationInfo
    ): Response<LocationInfoResponse>

}