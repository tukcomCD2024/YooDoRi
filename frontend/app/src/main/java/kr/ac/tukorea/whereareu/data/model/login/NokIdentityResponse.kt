package kr.ac.tukorea.whereareu.data.model.login

import com.google.gson.annotations.SerializedName

data class NokIdentityResponse(
    val message: String,
    val result: NokIdentityResponseResult,
    val status: String,
)
