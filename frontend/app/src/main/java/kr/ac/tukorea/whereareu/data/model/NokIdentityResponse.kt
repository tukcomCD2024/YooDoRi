package kr.ac.tukorea.whereareu.data.model

import com.google.gson.annotations.SerializedName

data class NokIdentityResponse(
    val message: String,
    @SerializedName("nok_key")
    val nokKey: String,
    val status: String
)
