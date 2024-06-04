package kr.ac.tukorea.whereareu.data.model.nok.home

data class MeaningfulPlaceResponse(
    val meaningfulPlaces: List<MeaningfulPlace>
){
    constructor(): this(emptyList())
}