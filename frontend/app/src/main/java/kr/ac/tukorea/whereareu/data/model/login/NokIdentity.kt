package kr.ac.tukorea.whereareu.data.model.login

import com.google.gson.annotations.SerializedName

data class NokIdentity(
    val keyFromDementia: String,
    val name: String?,
    val phoneNumber: String?
)
