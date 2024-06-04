package kr.ac.tukorea.whereareu.domain.home

import com.naver.maps.geometry.LatLng

data class LastLocation(
    val latitude: LatLng,
    val address: String
)
