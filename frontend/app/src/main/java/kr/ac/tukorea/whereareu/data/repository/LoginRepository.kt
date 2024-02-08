package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.model.CheckConnect
import kr.ac.tukorea.whereareu.data.model.CheckConnectedResponse
import kr.ac.tukorea.whereareu.data.model.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.data.model.NokIdentity
import kr.ac.tukorea.whereareu.data.model.NokIdentityResponse
import kr.ac.tukorea.whereareu.util.NetworkResult

interface LoginRepository {
    suspend fun sendNokIdentity(request: NokIdentity): NetworkResult<NokIdentityResponse>
    suspend fun sendDementiaIdentity(request: DementiaIdentity): NetworkResult<DementiaIdentityResponse>
    suspend fun checkInterConnected(request: CheckConnect): NetworkResult<CheckConnectedResponse>
}