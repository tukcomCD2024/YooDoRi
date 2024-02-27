package kr.ac.tukorea.whereareu.data.model

import com.google.gson.annotations.SerializedName

data class NokIdentityResponse(
    val dementiaInfoRecord: DementiaInfo,
    val nokKey: String,
)
