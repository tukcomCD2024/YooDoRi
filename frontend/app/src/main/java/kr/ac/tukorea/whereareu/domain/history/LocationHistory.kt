package kr.ac.tukorea.whereareu.domain.history

data class LocationHistory(
    val latitude: Double,
    val longitude: Double,
    val time: String,
    val userStatus: String,
    val distance: Float,
    val isLast: Boolean = false,
    val viewType: Int = OTHER_STATUS,
    val date: String = ""
) {
    constructor() : this(0.0, 0.0, "", "", 0f)
    companion object{
        const val STOP_STATUS = 0
        const val OTHER_STATUS = 1
    }
}
