package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.model.NokIdentity
import kr.ac.tukorea.whereareu.data.model.NokIdentityResponse
import kr.ac.tukorea.whereareu.data.api.LoginService
import kr.ac.tukorea.whereareu.data.model.CheckConnect
import kr.ac.tukorea.whereareu.data.model.CheckConnectedResponse
import kr.ac.tukorea.whereareu.data.model.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.data.model.ResponseBody
import kr.ac.tukorea.whereareu.util.NetworkResult
import kr.ac.tukorea.whereareu.util.handleApi
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService
): LoginRepository {
    override suspend fun sendNokIdentity(request: NokIdentity): NetworkResult<NokIdentityResponse> {
        return handleApi({api.postNokIdentity(request)}) {response: ResponseBody<NokIdentityResponse> -> response.result}
    }

    override suspend fun sendDementiaIdentity(request: DementiaIdentity): NetworkResult<DementiaIdentityResponse> {
        return handleApi({api.postDementiaIdentity(request)}) { response: ResponseBody<DementiaIdentityResponse> -> response.result }
    }

    override suspend fun checkInterConnected(request: CheckConnect): NetworkResult<CheckConnectedResponse> {
        return handleApi({api.postIsConnected(request)}) { response: ResponseBody<CheckConnectedResponse> -> response.result}
    }

//    override suspend fun checkConnected(request: CheckConnect): Response<CheckConnectedResponse> {
//        return api.checkConnected(request)
//    }

}