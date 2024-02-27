package kr.ac.tukorea.whereareu.data.model.login

import kr.ac.tukorea.whereareu.data.model.CheckConnectResponseResult

data class CheckConnectedResponse(
    val message: String,
    val result: CheckConnectResponseResult,
    val status: String
)
