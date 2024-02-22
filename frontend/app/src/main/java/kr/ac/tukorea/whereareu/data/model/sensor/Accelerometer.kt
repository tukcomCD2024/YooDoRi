package kr.ac.tukorea.whereareu.data.model.sensor

data class Accelerometer(
    val xAxis: Float,
    val yAxis: Float,
    val zAxis: Float,
){
    constructor(): this(0f, 0f, 0f)
}
