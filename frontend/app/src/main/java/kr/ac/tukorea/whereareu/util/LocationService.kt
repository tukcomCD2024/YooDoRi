package kr.ac.tukorea.whereareu.util

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = DefaultLocationClient(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
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

        locationClient
            .getLocationUpdates(10000L)
            .catch { e -> e.printStackTrace() }
            .onEach { location ->
                val lat = location.latitude.toString()
                val long = location.longitude.toString()
                Log.d("location", "$lat, $long")
                repository.postLocationInfo(
                    LocationInfo(
                    "227609",
                    location.latitude,
                    location.longitude,
                    "지금",
                    "이순간",
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    80.0f,
                    true,
                    true,
                    true)
                ).onSuccess {
                    Log.d("success", it.toString())
                }.onException {
                    Log.d("error", it.toString())
                }
                sendLocation(location.latitude, location.longitude)
                val updatedNotification = notification.setContentText(
                    "Location: ($lat, $long)"
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
    }
}