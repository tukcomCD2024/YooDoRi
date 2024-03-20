package kr.ac.tukorea.whereareu.util.location

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
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl
import kr.ac.tukorea.whereareu.util.network.onException
import kr.ac.tukorea.whereareu.util.network.onSuccess
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor
import kr.ac.tukorea.whereareu.util.sensor.LightSensor
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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
    private val locationExtraInfo = mutableListOf(0f, 0f)
    private val localBroadcastManager by lazy {
        LocalBroadcastManager.getInstance(applicationContext)
    }

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
                    var userState = 0
                    var isSuccess = false
                    val info = LocationInfo(dementiaKey, locationInfo[LATITUDE], locationInfo[LONGITUDE],
                        currentTime[TIME].trim(), currentTime[DATE], locationExtraInfo[SPEED],
                        accelerationsensor = sensorValueList[ACCELEROMETER_SENSOR],
                        gyrosensor = sensorValueList[GYRO_SENSOR],
                        directionsensor = sensorValueList[MAGNETIC_SENSOR],
                        lightsensor = sensorValueList[LIGHT_SENSOR],
                        battery = getBatteryPercent()!!, bearing = locationExtraInfo[BEARING],
                        isGpsOn = locationClient.getGpsStatus(), isInternetOn = true, isRingstoneOn = getRingMode()
                    )
                    sendLocation(info)
                    Log.d("info", info.toString())
                    repository.postLocationInfo(info).onSuccess {
                        userState = it.result
                        isSuccess = true
                    }.onException {
                        isSuccess = false
                        Log.d("error", it.toString())
                    }
                    // AI 정보 수집을 위한 함수
                    saveFile(currentTime[DATE], currentTime[TIME].trim(), userState.toString(), isSuccess.toString())
                    delay(10000)
                }
            }
        }
    }

    private fun saveFile(date: String, time: String, userState: String, isError: String){
        val internalFile = InternalFileStorageUtil(applicationContext)
        internalFile.appendContentToFile("${locationInfo[LATITUDE]}, ${locationInfo[LONGITUDE]}, " +
                "$date $time, " + "$userState, $isError")
    }

    private fun getDementiaKey(): String?{
        val dementiaKeySpf = applicationContext.getSharedPreferences("User", MODE_PRIVATE)
        return dementiaKeySpf.getString("key", "")
    }

    private fun checkReadyToPost(): Boolean{
        return !locationInfo[LATITUDE].equals(0.0) && !locationInfo[LONGITUDE].equals(0.0) && sensorValueList[MAGNETIC_SENSOR].isNotEmpty()
                && sensorValueList[LIGHT_SENSOR].isNotEmpty() && sensorValueList[ACCELEROMETER_SENSOR].isNotEmpty()
                && sensorValueList[GYRO_SENSOR].isNotEmpty()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentTime(): List<String>{
        Locale.setDefault(Locale.KOREA)
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val now = sdf.format(date)
        Log.d("now", now)
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

    private fun getRingMode(): Int{
        val audioManager = applicationContext.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        return audioManager.ringerMode
            // ringMode 2: 벨소리
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
            .getLocationUpdates(5*1000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                locationInfo[LATITUDE] = location.latitude
                locationInfo[LONGITUDE] = location.longitude
                locationExtraInfo[SPEED] = location.speed
                locationExtraInfo[BEARING] = location.bearing
                Log.d("speed", "${location.speed}")
                val updatedNotification = notification.setContentText(
                    "Location: (${location.latitude}, ${location.longitude}) speed: ${location.speed}"
                )
                notificationManager.notify(1, updatedNotification.build())
            }.launchIn(serviceScope)
        startForeground(1, notification.build())
    }

    private fun sendLocation(locationInfo: LocationInfo){
        val intent = Intent("gps")
        intent.putExtra("postInfo", locationInfo)
        //intent.putExtra("long", long)
        localBroadcastManager.sendBroadcast(intent)
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

        const val LATITUDE = 0
        const val LONGITUDE = 1

        const val SPEED = 0
        const val BEARING = 1

        const val DATE = 0
        const val TIME = 1
    }
}