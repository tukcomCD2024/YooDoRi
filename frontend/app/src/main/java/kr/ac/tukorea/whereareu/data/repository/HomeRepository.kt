package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.model.LocationInfo
import kr.ac.tukorea.whereareu.data.model.LocationInfoResponse
import kr.ac.tukorea.whereareu.data.model.sensor.Accelerometer
import kr.ac.tukorea.whereareu.util.NetworkResult

interface HomeRepository {
    suspend fun postLocationInfo(request: LocationInfo): NetworkResult<LocationInfoResponse>
}