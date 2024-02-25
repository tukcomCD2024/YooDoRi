package kr.ac.tukorea.whereareu.data.model

import com.google.gson.annotations.SerializedName

data class NokIdentity(
    @SerializedName("keyFromDementia")
    val dementiaKey: String,
    val name: String?,
    val phoneNumber: String?
) {
    constructor(): this("000000", "사용자", "010-9999-9999")
}
