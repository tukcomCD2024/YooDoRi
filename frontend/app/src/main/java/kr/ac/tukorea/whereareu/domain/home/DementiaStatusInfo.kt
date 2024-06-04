package kr.ac.tukorea.whereareu.domain.home

data class DementiaStatusInfo(
    val userStatus: String,
    val battery: Int,
    val isGpsOn: Boolean,
    val isInternetOn: Boolean,
    val isRingstoneOn: Int,
){
    constructor(): this("",100,false,false,0)
}
