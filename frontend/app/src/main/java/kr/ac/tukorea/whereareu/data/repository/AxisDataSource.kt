package kr.ac.tukorea.whereareu.data.repository

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kr.ac.tukorea.whereareu.WhereAreUApplication
import kr.ac.tukorea.whereareu.data.model.sensor.Accelerometer

class AxisDataSource: SensorEventListener {
    private val sensorManager by lazy {
        WhereAreUApplication.applicationContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    private var accSeonsor: Sensor? = null
    private var lastAxis = Accelerometer()
    private var axisList = mutableListOf<Accelerometer>()

    suspend fun getAxisList(): List<Accelerometer>{
        return axisList
        delay(1000)
    }
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor == accSeonsor) {
            val xAxis = event.values[0]
            val yAxis = event.values[1]
            val zAxis = event.values[2]
            //Log.d("sensor", "$xAxis, $yAxis, $zAxis")
            val axis = Accelerometer(xAxis, yAxis, zAxis)
            if(axis != lastAxis){
                axisList.add(axis)
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }


}