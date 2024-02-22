package kr.ac.tukorea.whereareu.data.model.login

import com.google.gson.annotations.SerializedName

data class NokIdentityResponse(
    val dementiaInfo: DementiaInfo,
    val message: String,
    val nokKey: String,
    val status: String,
)
