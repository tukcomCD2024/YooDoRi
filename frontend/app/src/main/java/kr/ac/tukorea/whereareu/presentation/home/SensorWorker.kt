package kr.ac.tukorea.whereareu.presentation.home

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay
import kr.ac.tukorea.whereareu.data.model.sensor.Accelerometer

class SensorWorker(context: Context, workerParams: WorkerParameters) : CoroutineWorker(context, workerParams),
    SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var axis = Accelerometer()

    override suspend fun doWork(): Result {
        // 센서 매니저 초기화
        /*sensorManager = applicationContext.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // 가속도 센서 초기화
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        // 가속도 센서가 존재하는 경우에만 리스너 등록
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }*/

        // 센서 값을 가져오는 작업 수행
        /*val test = floatArrayOf(axis.xAxis, axis.yAxis, axis.zAxis)
        val outputData = Data.Builder()
            .putFloatArray("x", test)
            .build()*/

        for (i in 1..100){
            Log.d("test i", i.toString())
            delay(1000)
        }

        return Result.success()
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val xAxis = event.values[0]
            val yAxis = event.values[1]
            val zAxis = event.values[2]

            // 여기에서 가속도 센서 값(x, y, z 축)을 사용할 수 있습니다.
            // 예를 들어, 로그에 출력하는 경우:
            axis = Accelerometer(xAxis, yAxis, zAxis)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // 센서 정확도 변경 시 호출되는 메서드
    }

}