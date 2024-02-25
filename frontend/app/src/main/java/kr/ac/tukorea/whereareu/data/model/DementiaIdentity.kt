package kr.ac.tukorea.whereareu.data.model

import com.google.gson.annotations.SerializedName

data class DementiaIdentity(
    val name: String?,
    val phoneNumber: String?
){
    constructor(): this("사용자", "010-0000-0000")
}
