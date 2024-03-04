package kr.ac.tukorea.whereareu.data.repository.home

import kr.ac.tukorea.whereareu.data.api.DementiaHomeService
import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.data.model.home.PostLocationInfoResponse
import kr.ac.tukorea.whereareu.util.network.NetworkResult
import kr.ac.tukorea.whereareu.util.network.handleApi
import javax.inject.Inject

class DementiaHomeRepositoryImpl @Inject constructor(
    private val api: DementiaHomeService
): DementiaHomeRepository {
    override suspend fun postLocationInfo(request: LocationInfo): NetworkResult<PostLocationInfoResponse> {
        return handleApi({api.postLocationInfo(request)}) {response: PostLocationInfoResponse -> response}
    }
}