package kr.ac.tukorea.whereareu.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.data.repository.home.DementiaHomeRepositoryImpl
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor
import kr.ac.tukorea.whereareu.util.sensor.LightSensor
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor
import kr.ac.tukorea.whereareu.util.network.onError
import kr.ac.tukorea.whereareu.util.network.onSuccess
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: DementiaHomeRepositoryImpl,
    private val magneticFieldSensor: MagneticFieldSensor,
    private val lightSensor: LightSensor,
    private val accelerometerSensor: AccelerometerSensor,
    private val gyroScopeSensor: GyroScopeSensor
): ViewModel() {
    val init: Int = 0

    /*init {
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            val lux = values
            Log.d("light Sensor", lux.toString())
        }

        magneticFieldSensor.startListening()
        magneticFieldSensor.setOnSensorValuesChangedListener {values ->
            val magneticField = values
            Log.d("magnetic", magneticField.toString())
        }

        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            val accel = values
            Log.d("accel", accel.toString())
        }

        gyroScopeSensor.startListening()
        gyroScopeSensor.setOnSensorValuesChangedListener {values ->
            val gyro = values
            Log.d("gyro", gyro.toString())
        }
    }*/


    fun postLocationInfo(request: LocationInfo){
        viewModelScope.launch(Dispatchers.IO) {
            repository.postLocationInfo(request).onSuccess {
                Log.d("success", it.toString())
            }.onError {
                Log.d("error", it.toString())
            }
        }
    }
}