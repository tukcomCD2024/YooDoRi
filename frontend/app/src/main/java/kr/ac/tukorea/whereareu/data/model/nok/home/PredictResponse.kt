package kr.ac.tukorea.whereareu.data.model.nok.home

data class PredictResponse(
    val predictLocation: PredictLocationInfo,
    val policeInfo: List<PoliceStationInfoResponse>
)
