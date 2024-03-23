package kr.ac.tukorea.whereareu.data.model.setting

data class ModifyUserInfoRequest(
    val isDementia : String,
    val isNameChanged : String,
    val key : String,
    val name : String,
    val phoneNumber : String,
)
