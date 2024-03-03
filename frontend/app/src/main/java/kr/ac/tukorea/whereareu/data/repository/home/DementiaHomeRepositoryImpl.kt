package kr.ac.tukorea.whereareu.data.repository.home

import kr.ac.tukorea.whereareu.data.api.NokHomeService
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.data.model.home.LocationInfoResponse
import kr.ac.tukorea.whereareu.util.NetworkResult
import kr.ac.tukorea.whereareu.util.handleApi
import javax.inject.Inject

class DementiaHomeRepositoryImpl @Inject constructor(
    private val api: NokHomeService
): DementiaHomeRepository {
    override suspend fun postLocationInfo(request: LocationInfo): NetworkResult<LocationInfoResponse> {
        return handleApi({api.postLocationInfo(request)}) {response: LocationInfoResponse -> response}
    }
}