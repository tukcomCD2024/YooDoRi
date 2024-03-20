package kr.ac.tukorea.whereareu.util.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import android.location.LocationRequest
import android.os.Build
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.util.extension.hasLocationPermission

class DefaultLocationClient(
    private val context: Context,
    private val client: FusedLocationProviderClient
): LocationClient {
    private val locationManager by lazy {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }
    @SuppressLint("MissingPermission")
    override fun getLocationUpdates(interval: Long): Flow<Location> {
        return callbackFlow {
            if (!context.hasLocationPermission()) {
                throw LocationClient.LocationException("Missing location permission")
            }

            if (!getGpsStatus()) {
                throw LocationClient.LocationException("GPS is disabled")
            }

            val request = com.google.android.gms.location.LocationRequest.Builder(interval).apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    setPriority(LocationRequest.QUALITY_BALANCED_POWER_ACCURACY)
                }
            }.build()

            val locationCallback = object : LocationCallback() {
                override fun onLocationResult(result: LocationResult) {
                    super.onLocationResult(result)
                    result.locations.lastOrNull()?.let { location ->
                        launch { send(location) }
                    }
                }
            }

            client.requestLocationUpdates(
                request,
                locationCallback,
                Looper.getMainLooper()
            )

            awaitClose {
                client.removeLocationUpdates(locationCallback)
            }
        }
    }
    override fun getGpsStatus(): Boolean {
        return locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        Log.d("network", locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER).toString())
        Log.d("gps", locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER).toString())
    }
}