package kr.ac.tukorea.whereareu.data.model

data class ResponseBody<T>(
    val message: String,
    val status: String,
    val result: T
)
