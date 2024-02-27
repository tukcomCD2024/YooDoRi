package kr.ac.tukorea.whereareu.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date

data class LocationInfo(
    val dementiaKey: String,
    val latitude: Double,
    val longitude: Double,
    val time: String,
    val date: String,
    val currentSpeed: Float,
    val accelerationsensorX: Float,
    val accelerationsensorY: Float,
    val accelerationsensorZ: Float,
    val gyrosensorX: Float,
    val gyrosensorY: Float,
    val gyrosensorZ: Float,
    val directionsensorX: Float,
    val directionsensorY: Float,
    val directionsensorZ: Float,
    val lightsensor: Float,
    val battery: Float,
    val isInternetOn: Boolean,
    val isGpsOn: Boolean,
    val isRingstoneOn: Boolean
)

