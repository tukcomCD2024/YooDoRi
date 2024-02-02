package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.model.NokIdentity
import kr.ac.tukorea.whereareu.data.model.NokIdentityResponse
import kr.ac.tukorea.whereareu.data.api.LoginService
import kr.ac.tukorea.whereareu.data.model.CheckConnect
import kr.ac.tukorea.whereareu.data.model.CheckConnectedResponse
import kr.ac.tukorea.whereareu.data.model.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.DementiaIdentityResponse
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: LoginService
): LoginRepository {
    override suspend fun sendNokIdentity(request: NokIdentity): Response<NokIdentityResponse> {
        return api.postNokIdentity(request)
    }

    override suspend fun sendDementiaIdentity(request: DementiaIdentity): Response<DementiaIdentityResponse> {
        return api.postDementiaIdentity(request)
    }

    override suspend fun checkConnected(request: CheckConnect): Response<CheckConnectedResponse> {
        return api.checkConnected(request)
    }

}