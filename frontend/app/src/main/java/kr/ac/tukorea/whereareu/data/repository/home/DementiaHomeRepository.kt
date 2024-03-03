package kr.ac.tukorea.whereareu.data.repository.home

import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.data.model.home.PostLocationInfoResponse
import kr.ac.tukorea.whereareu.util.NetworkResult

interface DementiaHomeRepository {
    suspend fun postLocationInfo(request: LocationInfo): NetworkResult<PostLocationInfoResponse>
}