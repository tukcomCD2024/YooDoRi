package kr.ac.tukorea.whereareu.data.model

import kr.ac.tukorea.whereareu.data.model.login.DementiaInfo

data class NokIdentityResponseResult(
    val dementiaInfoRecord: DementiaInfo,
    val nokKey: String,
)
