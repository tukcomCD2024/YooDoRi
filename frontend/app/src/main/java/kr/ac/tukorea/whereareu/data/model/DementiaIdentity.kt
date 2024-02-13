package kr.ac.tukorea.whereareu.data.model

import com.google.gson.annotations.SerializedName

data class DementiaIdentity(
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String
)
