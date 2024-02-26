package kr.ac.tukorea.whereareu.data.model

import com.google.gson.annotations.SerializedName

data class DementiaIdentityResponse(
    val dementiaKey: String,
    val message: String,
    val status: String
)
