package kr.ac.tukorea.whereareu.data.repository

import kr.ac.tukorea.whereareu.data.model.DementiaIdentity
import kr.ac.tukorea.whereareu.data.model.DementiaIdentityResponse
import kr.ac.tukorea.whereareu.data.model.NokIdentity
import kr.ac.tukorea.whereareu.data.model.NokIdentityResponse
import retrofit2.Response

interface LoginRepository {
    suspend fun sendNokIdentity(request: NokIdentity): Response<NokIdentityResponse>
    suspend fun sendDementiaIdentity(request: DementiaIdentity): Response<DementiaIdentityResponse>
}