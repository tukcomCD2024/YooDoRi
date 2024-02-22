package kr.ac.tukorea.whereareu.data.model.login

import com.google.gson.annotations.SerializedName

data class NokIdentityResponse(
    @SerializedName("dementia_info")
    val dementiaInfo: DementiaInfo,

    val message: String,
    @SerializedName("nok_key")
    val nokKey: String,
    val status: String,
)
