package kr.ac.tukorea.whereareu.data.model.setting

data class ModifyUserInfoRequest(
    val isDementia : Int,
    val isNameChanged : Int,
    val key : String?,
    val name : String,
    val phoneNumber : String,
)
