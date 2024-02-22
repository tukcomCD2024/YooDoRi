package kr.ac.tukorea.whereareu.data.model.login

import com.google.gson.annotations.SerializedName

data class NokIdentity(
    @SerializedName("keyfromdementia")
    val dementiaKey: String,
    val name: String?,
    val phoneNumber: String?
)
