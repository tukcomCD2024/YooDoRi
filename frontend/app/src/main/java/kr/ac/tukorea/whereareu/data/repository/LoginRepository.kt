package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.model.login.CheckConnect
import kr.ac.tukorea.whereareu.data.model.login.CheckConnectedResponse
import kr.ac.tukorea.whereareu.data.model.login.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.login.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.data.model.login.NokIdentity
import kr.ac.tukorea.whereareu.data.model.login.NokIdentityResponse
import kr.ac.tukorea.whereareu.util.NetworkResult

interface LoginRepository {
    suspend fun sendNokIdentity(request: NokIdentity): NetworkResult<NokIdentityResponse>
    suspend fun sendDementiaIdentity(request: DementiaIdentity): NetworkResult<DementiaIdentityResponse>
    suspend fun checkInterConnected(request: CheckConnect): NetworkResult<CheckConnectedResponse>
}