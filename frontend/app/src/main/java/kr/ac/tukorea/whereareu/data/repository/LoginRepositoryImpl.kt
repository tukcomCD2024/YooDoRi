package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.model.login.NokIdentity
import kr.ac.tukorea.whereareu.data.model.login.NokIdentityResponse
import kr.ac.tukorea.whereareu.data.api.LoginService
import kr.ac.tukorea.whereareu.data.model.login.CheckConnect
import kr.ac.tukorea.whereareu.data.model.login.CheckConnectedResponse
import kr.ac.tukorea.whereareu.data.model.login.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.login.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.util.NetworkResult
import kr.ac.tukorea.whereareu.util.handleApi
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService
): LoginRepository {
    override suspend fun sendNokIdentity(request: NokIdentity): NetworkResult<NokIdentityResponse> {
        return handleApi({api.postNokIdentity(request)}) { NokIdentityResponse(it.dementiaInfo, it.message, it.nokKey, it.status) }
    }

    override suspend fun sendDementiaIdentity(request: DementiaIdentity): NetworkResult<DementiaIdentityResponse> {
        return handleApi({api.postDementiaIdentity(request)}) { DementiaIdentityResponse(it.dementiaKey, it.message, it.status) }
    }

    override suspend fun checkInterConnected(request: CheckConnect): NetworkResult<CheckConnectedResponse> {
        return handleApi({api.postIsConnected(request)}) { CheckConnectedResponse(it.message, it.status) }
    }

//    override suspend fun checkConnected(request: CheckConnect): Response<CheckConnectedResponse> {
//        return api.checkConnected(request)
//    }

}