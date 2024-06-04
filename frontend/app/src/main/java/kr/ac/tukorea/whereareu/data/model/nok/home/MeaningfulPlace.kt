package kr.ac.tukorea.whereareu.data.model.nok.home

import com.naver.maps.geometry.LatLng
import kr.ac.tukorea.whereareu.domain.home.MeaningfulPlaceInfo
import kr.ac.tukorea.whereareu.domain.home.PoliceStationInfo
import java.io.Serializable

data class MeaningfulPlace(
    val address: String,
    val timeInfo: List<TimeInfo>,
    val latitude: Double,
    val longitude: Double,
    val policeStationInfo: List<PoliceStationInfoResponse>
){
    fun toModel(policeStationInfo: List<PoliceStationInfo>) = MeaningfulPlaceInfo(
        this.address,
        this.timeInfo,
        LatLng(this.latitude ,this.longitude),
        false,
        policeStationInfo
    )
}