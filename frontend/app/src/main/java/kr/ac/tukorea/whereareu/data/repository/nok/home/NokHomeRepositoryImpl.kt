package kr.ac.tukorea.whereareu.data.repository.nok.home

import kr.ac.tukorea.whereareu.data.api.nok.NokHomeService
import kr.ac.tukorea.whereareu.data.model.DementiaKeyRequest
import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.data.model.nok.home.DementiaLastInfoResponse
import kr.ac.tukorea.whereareu.data.model.nok.home.LocationInfoResponse
import kr.ac.tukorea.whereareu.data.model.nok.home.MeaningfulPlaceResponse
import kr.ac.tukorea.whereareu.data.model.nok.home.PredictResponse
import kr.ac.tukorea.whereareu.data.model.setting.GetUserInfoResponse
import kr.ac.tukorea.whereareu.util.network.NetworkResult
import kr.ac.tukorea.whereareu.util.network.handleApi
import javax.inject.Inject

class NokHomeRepositoryImpl @Inject constructor(
    private val api: NokHomeService
): NokHomeRepository {
    override suspend fun getDementiaLocationInfo(dementiaKey: String): NetworkResult<LocationInfoResponse> {
        return handleApi({api.getDementiaLocationInfo(dementiaKey)}) {response: ResponseBody<LocationInfoResponse> -> response.result}
    }

    override suspend fun getMeaningfulPlace(dementiaKey: String): NetworkResult<MeaningfulPlaceResponse> {
        return handleApi({api.getMeaningfulPlace(dementiaKey)}) {response: ResponseBody<MeaningfulPlaceResponse> -> response.result}
    }

    override suspend fun getDementiaLastInfo(request: DementiaKeyRequest): NetworkResult<DementiaLastInfoResponse> {
        return handleApi({api.getDementiaLastInfo(request)}) { response: ResponseBody<DementiaLastInfoResponse> -> response.result}
    }

    override suspend fun fetchPredictInfo(dementiaKey: String): NetworkResult<PredictResponse> {
        return handleApi({api.fetchPredictInfo(dementiaKey)}) { response: ResponseBody<PredictResponse> -> response.result}
    }

    override suspend fun fetchPredictInfoGura(dementiaKey: String): NetworkResult<PredictResponse> {
        return handleApi({api.fetchPredictInfoGura(dementiaKey)}) { response: ResponseBody<PredictResponse> -> response.result}
    }

    override suspend fun getUserInfo(nokKey: String): NetworkResult<GetUserInfoResponse> {
        return handleApi({api.getUserInfo(nokKey)}) {response: ResponseBody<GetUserInfoResponse> -> response.result}
    }

}