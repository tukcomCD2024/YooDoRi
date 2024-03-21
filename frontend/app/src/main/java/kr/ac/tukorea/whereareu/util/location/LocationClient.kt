package kr.ac.tukorea.whereareu.util.location

import android.location.Location
import android.location.LocationManager
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(interval: Long): Flow<Location>

    fun getGpsStatus(): Boolean

    class LocationException(message: String): Exception()
}