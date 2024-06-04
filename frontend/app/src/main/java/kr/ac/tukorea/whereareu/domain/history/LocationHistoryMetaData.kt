package kr.ac.tukorea.whereareu.domain.history

import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.PathOverlay

data class LocationHistoryMetaData(
    val paths: List<PathOverlay> = listOf(PathOverlay(), PathOverlay()),
    val markers: List<Marker> = listOf(Marker(), Marker()),
    var zoom: Double = 14.0,
    var locationHistory: List<LocationHistory> = emptyList(),
    var locationHistory2: List<LocationHistory> = emptyList()
)
