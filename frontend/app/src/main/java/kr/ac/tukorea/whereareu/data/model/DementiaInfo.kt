package kr.ac.tukorea.whereareu.data.model

import com.google.gson.annotations.SerializedName

data class DementiaInfo(
    @SerializedName("dementia_key")
    val dementiaKey: String,
    @SerializedName("dementia_name")
    val dementiaName: String,
    @SerializedName("dementia_phonenumber")
    val dementiaPhonenumber: String
)
