package kr.ac.tukorea.whereareu.domain.login

import com.google.gson.annotations.SerializedName

data class DementiaInfo(
    val dementiaKey: String,
    val dementiaName: String,
    val dementiaPhoneNumber: String
)
