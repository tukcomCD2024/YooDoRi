package kr.ac.tukorea.whereareu.data.model.login.response

import kr.ac.tukorea.whereareu.domain.login.DementiaInfo

data class NokIdentityResponse(
    val dementiaInfoRecord: DementiaInfo,
    val nokKey: String,
)
