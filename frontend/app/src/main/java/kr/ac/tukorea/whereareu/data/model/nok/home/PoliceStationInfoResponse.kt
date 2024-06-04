package kr.ac.tukorea.whereareu.data.model.nok.home

import com.naver.maps.geometry.LatLng
import kr.ac.tukorea.whereareu.domain.home.PoliceStationInfo

data class PoliceStationInfoResponse(
    val policeName: String,
    val distance: String,
    val policeAddress: String,
    val roadAddress: String,
    val policePhoneNumber: String,
    val latitude: String,
    val longitude: String
){
    fun toModel() = PoliceStationInfo(
        this.policeName,
        this.distance,
        this.policeAddress,
        this.policePhoneNumber,
        LatLng(this.latitude.toDouble(), this.longitude.toDouble())
    )
}
