package kr.ac.tukorea.whereareu.data.model.setting

data class ModifyUserInfoRequest(
    val key: String,
    val isDementia: Int,
    val name: String,
    val phoneNumber: String,
)
