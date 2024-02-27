package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest
import kr.ac.tukorea.whereareu.data.model.login.response.NokIdentityResponse
import kr.ac.tukorea.whereareu.data.api.LoginService
import kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest
import kr.ac.tukorea.whereareu.data.model.login.response.CheckInterConnectResponse
import kr.ac.tukorea.whereareu.data.model.login.request.DementiaIdentityRequest
import kr.ac.tukorea.whereareu.data.model.login.response.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.util.NetworkResult
import kr.ac.tukorea.whereareu.util.handleApi
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService
): LoginRepository {
    override suspend fun sendNokIdentity(request: NokIdentityRequest): NetworkResult<NokIdentityResponse> {
        return handleApi({api.postNokIdentity(request)}) {response: ResponseBody<NokIdentityResponse> -> response.result}
    }

    override suspend fun sendDementiaIdentity(request: DementiaIdentityRequest): NetworkResult<DementiaIdentityResponse> {
        return handleApi({api.postDementiaIdentity(request)}) { response: ResponseBody<DementiaIdentityResponse> -> response.result }
    }

    override suspend fun checkInterConnected(request: CheckInterConnectRequest): NetworkResult<CheckInterConnectResponse> {
        return handleApi({api.postIsConnected(request)}) { response: ResponseBody<CheckInterConnectResponse> -> response.result}
    }

}