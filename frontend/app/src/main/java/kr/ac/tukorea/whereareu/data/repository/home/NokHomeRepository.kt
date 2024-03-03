package kr.ac.tukorea.whereareu.data.repository.home

import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse
import kr.ac.tukorea.whereareu.util.NetworkResult
import retrofit2.Response

interface NokHomeRepository {
    suspend fun getDementiaLocationInfo(dementiaKey: String): NetworkResult<GetLocationInfoResponse>
}