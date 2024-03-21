package kr.ac.tukorea.whereareu.util.location;

import android.location.Location;
import android.location.LocationManager;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\tJ\b\u0010\u0002\u001a\u00020\u0003H&J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\n"}, d2 = {"Lkr/ac/tukorea/whereareu/util/location/LocationClient;", "", "getGpsStatus", "", "getLocationUpdates", "Lkotlinx/coroutines/flow/Flow;", "Landroid/location/Location;", "interval", "", "LocationException", "app_debug"})
public abstract interface LocationClient {
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<android.location.Location> getLocationUpdates(long interval);
    
    public abstract boolean getGpsStatus();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005\u00a8\u0006\u0006"}, d2 = {"Lkr/ac/tukorea/whereareu/util/location/LocationClient$LocationException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "app_debug"})
    public static final class LocationException extends java.lang.Exception {
        
        public LocationException(@org.jetbrains.annotations.NotNull()
        java.lang.String message) {
            super();
        }
    }
}