package kr.ac.tukorea.whereareu.data.model.login

import com.google.gson.annotations.SerializedName

data class CheckConnect(
    @SerializedName("dementiaKey")
    val key: String
)
