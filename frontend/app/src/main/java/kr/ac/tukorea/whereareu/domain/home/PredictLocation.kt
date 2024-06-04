package kr.ac.tukorea.whereareu.domain.home

data class PredictLocation(
    val meaningfulPlaceInfo: MeaningfulPlaceInfo,
    val policeStationInfo: List<PoliceStationInfo>
){
    constructor(): this(MeaningfulPlaceInfo(), emptyList())
}
