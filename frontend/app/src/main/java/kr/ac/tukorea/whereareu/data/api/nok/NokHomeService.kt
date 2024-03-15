package kr.ac.tukorea.whereareu.data.api.nok

import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NokHomeService {
    @GET("send-live-location-info")
    suspend fun getDementiaLocationInfo(@Query("dementiaKey") dementiaKey: String): Response<ResponseBody<GetLocationInfoResponse>>
}