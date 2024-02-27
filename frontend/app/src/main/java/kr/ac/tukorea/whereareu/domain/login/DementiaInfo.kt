<<<<<<<< HEAD:frontend/app/src/main/java/kr/ac/tukorea/whereareu/data/model/login/DementiaInfo.kt
package kr.ac.tukorea.whereareu.data.model.login
========
package kr.ac.tukorea.whereareu.domain.login
>>>>>>>> develop_front:frontend/app/src/main/java/kr/ac/tukorea/whereareu/domain/login/DementiaInfo.kt

import com.google.gson.annotations.SerializedName

data class DementiaInfo(
    val dementiaKey: String,
    val dementiaName: String,
    val dementiaPhoneNumber: String
)
