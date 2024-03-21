package kr.ac.tukorea.whereareu.data.model.login.request

data class DementiaIdentityRequest(
    val name: String,
    val phoneNumber: String
){
    constructor(): this("사용자", "010-0000-0000")
}
