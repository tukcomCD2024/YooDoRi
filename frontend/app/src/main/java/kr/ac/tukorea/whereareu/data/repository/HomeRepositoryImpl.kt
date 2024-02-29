package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.api.HomeService
import kr.ac.tukorea.whereareu.data.model.LocationInfo
import kr.ac.tukorea.whereareu.data.model.LocationInfoResponse
import kr.ac.tukorea.whereareu.data.model.sensor.Accelerometer
import kr.ac.tukorea.whereareu.util.NetworkResult
import kr.ac.tukorea.whereareu.util.handleApi
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeService
): HomeRepository {
    override suspend fun postLocationInfo(request: LocationInfo): NetworkResult<LocationInfoResponse> {
        return handleApi({api.postLocationInfo(request)}) {response: LocationInfoResponse -> response}
    }
}