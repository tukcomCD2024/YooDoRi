package kr.ac.tukorea.whereareu.util

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.ServiceCompat
import androidx.core.app.ServiceCompat.stopForeground
import androidx.core.content.ContextCompat.getSystemService
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ServiceScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable.start
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.LocationInfo
import kr.ac.tukorea.whereareu.data.repository.HomeRepository
import kr.ac.tukorea.whereareu.data.repository.HomeRepositoryImpl
import kr.ac.tukorea.whereareu.presentation.nok.MainNokActivity
import okhttp3.internal.notify
import javax.inject.Inject

@AndroidEntryPoint
class LocationService: Service() {
    @Inject
    lateinit var repository: HomeRepositoryImpl
    @Inject
    lateinit var accelerometerSensor: AccelerometerSensor
    @Inject
    lateinit var magneticFieldSensor: MagneticFieldSensor
    @Inject
    lateinit var gyroScopeSensor: GyroScopeSensor
    @Inject
    lateinit var lightSensor: LightSensor
    private lateinit var locationClient: LocationClient
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private var sensorValueList = mutableListOf<List<Float>>(emptyList(), emptyList(), emptyList(), emptyList())
    private val locationList = mutableListOf(0.0, 0.0)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        initSensor()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )

        val dementiaKeySpf = applicationContext.getSharedPreferences("OtherUser", MODE_PRIVATE)
        val dementiaKey = dementiaKeySpf.getString("key", "")
        if (!dementiaKey.isNullOrBlank())
            postLocationInfo(dementiaKey.toString())
    }

    private fun initSensor(){
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            sensorValueList[LIGHT_SENSOR] = values
        }

        magneticFieldSensor.startListening()
        magneticFieldSensor.setOnSensorValuesChangedListener {values ->
            val magneticField = values
            sensorValueList[MAGNETIC_SENSOR] = values
        }

        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            val accel = values
            sensorValueList[ACCELEROMETER_SENSOR] = values
        }

        gyroScopeSensor.startListening()
        gyroScopeSensor.setOnSensorValuesChangedListener {values ->
            val gyro = values
            sensorValueList[GYRO_SENSOR] = values
            Log.d("sensorValues", sensorValueList.toString())
        }
    }

    private fun postLocationInfo(dementiaKey: String){
        serviceScope.launch {
            while (true){
                delay(2000)
                if (locationList.isNotEmpty()&&sensorValueList.isNotEmpty()) {
                    val info = LocationInfo(
                        dementiaKey,
                        locationList[0], locationList[1],
                        "21:29:01", "2024-01-11",
                        0f,
                        directionsensorX = sensorValueList[MAGNETIC_SENSOR][0],
                        directionsensorY = sensorValueList[MAGNETIC_SENSOR][1],
                        directionsensorZ = sensorValueList[MAGNETIC_SENSOR][2],
                        gyrosensorX = sensorValueList[GYRO_SENSOR][0],
                        gyrosensorY = sensorValueList[GYRO_SENSOR][1],
                        gyrosensorZ = sensorValueList[GYRO_SENSOR][2],
                        accelerationsensorX = sensorValueList[ACCELEROMETER_SENSOR][0],
                        accelerationsensorY = sensorValueList[ACCELEROMETER_SENSOR][1],
                        accelerationsensorZ = sensorValueList[ACCELEROMETER_SENSOR][2],
                        lightsensor = sensorValueList[LIGHT_SENSOR][0],
                        battery = 80.0f,
                        isGpsOn = locationClient.getGpsStatus(),
                        isInternetOn = true,
                        isRingstoneOn = true
                    )
                    Log.d("info", info.toString())
                    repository.postLocationInfo(
                        info
                    ).onSuccess {
                        Log.d("success", it.toString())
                    }.onException {
                        Log.d("error", it.toString())
                    }
                }
                delay(58000)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            ACTION_START -> start()
            ACION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start(){
        val notification = NotificationCompat.Builder(this, "location")
            .setContentTitle("어디U 어플에서 위치정보를 수집중입니다.")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val dementiaKeySpf = applicationContext.getSharedPreferences("OtherUser", MODE_PRIVATE)
        val dementiaKey = dementiaKeySpf.getString("key", "")

        locationClient
            .getLocationUpdates(1000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                locationList[0] = location.latitude
                locationList[1] = location.longitude
                Log.d("speed", "${location.speed}")
                sendLocation(location.latitude, location.longitude)
                val updatedNotification = notification.setContentText(
                    "Location: (${location.latitude}, ${location.longitude}) speed: ${location.speed}"
                )
                notificationManager.notify(1, updatedNotification.build())
            }.launchIn(serviceScope)
        startForeground(1, notification.build())
    }

    private fun sendLocation(lat: Double, long: Double){
        val intent = Intent("gps")
        intent.putExtra("location", doubleArrayOf(lat, long))
        //intent.putExtra("long", long)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    private fun stop(){
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object{
        const val ACTION_START = "ACTION_START"
        const val ACION_STOP = "ACTION_STOP"
        const val LIGHT_SENSOR = 0
        const val GYRO_SENSOR = 1
        const val ACCELEROMETER_SENSOR = 2
        const val MAGNETIC_SENSOR = 3
    }
}