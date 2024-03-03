package kr.ac.tukorea.whereareu.data.model.home

data class GetLocationInfoResponse(
    val battery: Int,
    val isGpsOn: Boolean,
    val isInternetOn: Boolean,
    val isRingstoneOn: Boolean,
    val latitude: Double,
    val longitude: Double,
    val message: String,
    val status: String,
    val userStatus: Int
)
