package kr.ac.tukorea.whereareu.util

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.BatteryManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.data.repository.home.DementiaHomeRepositoryImpl
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class LocationService: Service() {
    @Inject
    lateinit var repository: DementiaHomeRepositoryImpl
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
    private val locationInfo = mutableListOf(0.0, 0.0)
    private var currentSpeed = 0f

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
        val dementiaKey = getDementiaKey()
        if (!getDementiaKey().isNullOrBlank())
            postLocationInfo(dementiaKey.toString())
    }

    private fun initSensor(){
        lightSensor.startListening()
        lightSensor.setOnSensorValuesChangedListener { values ->
            sensorValueList[LIGHT_SENSOR] = values
        }

        magneticFieldSensor.startListening()
        magneticFieldSensor.setOnSensorValuesChangedListener {values ->
            sensorValueList[MAGNETIC_SENSOR] = values
        }

        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            sensorValueList[ACCELEROMETER_SENSOR] = values
        }

        gyroScopeSensor.startListening()
        gyroScopeSensor.setOnSensorValuesChangedListener {values ->
            sensorValueList[GYRO_SENSOR] = values
        }
    }

    private fun postLocationInfo(dementiaKey: String){
        serviceScope.launch {
            while (true){
                if (checkReadyToPost()) {
                    val currentTime = getCurrentTime()
                    val date = currentTime[0]
                    val time = currentTime[1].trim()
                    val latitude = locationInfo[0]
                    val longitude = locationInfo[1]
                    val info = LocationInfo(dementiaKey, latitude, longitude, time, date, currentSpeed,
                        accelerationsensor = sensorValueList[ACCELEROMETER_SENSOR],
                        gyrosensor = sensorValueList[GYRO_SENSOR],
                        directionsensor = sensorValueList[MAGNETIC_SENSOR],
                        lightsensor = sensorValueList[LIGHT_SENSOR],
                        battery = getBatteryPercent()!!,
                        isGpsOn = locationClient.getGpsStatus(), isInternetOn = true, isRingstoneOn = getRingMode()
                    )
                    Log.d("info", info.toString())
                    repository.postLocationInfo(info).onSuccess {
                        Log.d("success", it.toString())
                    }.onException {
                        Log.d("error", it.toString())
                    }
                    delay(55000)
                }
            }
        }
    }

    private fun getDementiaKey(): String?{
        val dementiaKeySpf = applicationContext.getSharedPreferences("User", MODE_PRIVATE)
        return dementiaKeySpf.getString("key", "")
    }

    private fun checkReadyToPost(): Boolean{
        return !locationInfo[0].equals(0.0) && !locationInfo[1].equals(0.0) && sensorValueList[MAGNETIC_SENSOR].isNotEmpty()
                && sensorValueList[LIGHT_SENSOR].isNotEmpty() && sensorValueList[ACCELEROMETER_SENSOR].isNotEmpty()
                && sensorValueList[GYRO_SENSOR].isNotEmpty()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentTime(): List<String>{
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val now = sdf.format(date)
        return now.split(" ")
    }

    private fun getBatteryPercent(): Int?{
        val batteryStatus = registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        return batteryStatus?.let {
            val level = it.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)
            val scale = it.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
            level * 100 / scale
        }
    }

    private fun getRingMode(): Boolean{
        val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        return when(audioManager.ringerMode){
            // ringMode 2: 벨소리
            2 -> true
            else -> false
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when(intent?.action){
            ACTION_START -> start()
            ACTION_STOP -> stop()
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

        locationClient
            .getLocationUpdates(1000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                locationInfo[0] = location.latitude
                locationInfo[1] = location.longitude
                currentSpeed = location.speed
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
        const val ACTION_STOP = "ACTION_STOP"
        const val LIGHT_SENSOR = 0
        const val GYRO_SENSOR = 1
        const val ACCELEROMETER_SENSOR = 2
        const val MAGNETIC_SENSOR = 3
    }
}