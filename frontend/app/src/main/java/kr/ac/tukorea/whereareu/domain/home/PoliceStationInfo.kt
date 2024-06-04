package kr.ac.tukorea.whereareu.domain.home

import com.naver.maps.geometry.LatLng

data class PoliceStationInfo(
    val policeName: String,
    val distance: String,
    val policeAddress: String,
    val policePhoneNumber: String,
    val latLng: LatLng
)
