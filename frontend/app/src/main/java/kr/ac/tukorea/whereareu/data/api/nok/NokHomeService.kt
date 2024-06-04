package kr.ac.tukorea.whereareu.data.api.nok

import kr.ac.tukorea.whereareu.data.model.DementiaKeyRequest
import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.nok.home.DementiaLastInfoResponse
import kr.ac.tukorea.whereareu.data.model.nok.home.LocationInfoResponse
import kr.ac.tukorea.whereareu.data.model.nok.home.MeaningfulPlaceResponse
import kr.ac.tukorea.whereareu.data.model.nok.home.PredictResponse
import kr.ac.tukorea.whereareu.data.model.setting.GetUserInfoResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NokHomeService {
    @GET("locations/noks")
    suspend fun getDementiaLocationInfo(@Query("dementiaKey") dementiaKey: String): Response<ResponseBody<LocationInfoResponse>>

    @GET("locations/meaningful")
    suspend fun getMeaningfulPlace(@Query("dementiaKey") dementiaKey: String): Response<ResponseBody<MeaningfulPlaceResponse>>

    @POST("dementias/averageWalkingSpeed")
    suspend fun getDementiaLastInfo(@Body request: DementiaKeyRequest): Response<ResponseBody<DementiaLastInfoResponse>>

    @GET("users/info")
    suspend fun getUserInfo(@Query("nokKey") nokKey: String): Response<ResponseBody<GetUserInfoResponse>>
}
