package kr.ac.tukorea.whereareu.data.model.login

data class CheckConnectedResponse(
    val message: String,
    val result: CheckConnectResponseResult,
    val status: String
)
