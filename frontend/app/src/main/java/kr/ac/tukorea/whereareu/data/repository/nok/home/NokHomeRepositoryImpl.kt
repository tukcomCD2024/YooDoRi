package kr.ac.tukorea.whereareu.data.repository.home

import kr.ac.tukorea.whereareu.data.api.NokHomeService
import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse
import kr.ac.tukorea.whereareu.util.network.NetworkResult
import kr.ac.tukorea.whereareu.util.network.handleApi
import javax.inject.Inject

class NokHomeRepositoryImpl @Inject constructor(
    private val api: NokHomeService
): NokHomeRepository{
    override suspend fun getDementiaLocationInfo(dementiaKey: String): NetworkResult<GetLocationInfoResponse> {
        return handleApi({api.getDementiaLocationInfo(dementiaKey)}) {response: ResponseBody<GetLocationInfoResponse> -> response.result}
    }

}