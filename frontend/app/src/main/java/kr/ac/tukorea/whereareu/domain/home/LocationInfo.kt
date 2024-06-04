package kr.ac.tukorea.whereareu.domain.home

data class LocationInfo(
    val battery: Int,
    val isGpsOn: Boolean,
    val isInternetOn: Boolean,
    val isRingstoneOn: Int,
    val latitude: Double,
    val longitude: Double,
    val userStatus: String,
    val bearing: Float,
    val currentSpeed: Float,
    val isPredicted: Boolean
)
