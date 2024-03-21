package kr.ac.tukorea.whereareu.util.location;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.location.LocationServices;
import dagger.hilt.android.AndroidEntryPoint;
import kotlinx.coroutines.Dispatchers;
import kr.ac.tukorea.whereareu.R;
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo;
import kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl;
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor;
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor;
import kr.ac.tukorea.whereareu.util.sensor.LightSensor;
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 Q2\u00020\u0001:\u0001QB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u00102\u001a\u000203H\u0002J\u000f\u00104\u001a\u0004\u0018\u000105H\u0002\u00a2\u0006\u0002\u00106J\u000e\u00107\u001a\b\u0012\u0004\u0012\u0002080/H\u0003J\n\u00109\u001a\u0004\u0018\u000108H\u0002J\b\u0010:\u001a\u000205H\u0002J\b\u0010;\u001a\u00020<H\u0002J\u0014\u0010=\u001a\u0004\u0018\u00010>2\b\u0010?\u001a\u0004\u0018\u00010@H\u0016J\b\u0010A\u001a\u00020<H\u0016J\b\u0010B\u001a\u00020<H\u0016J\"\u0010C\u001a\u0002052\b\u0010?\u001a\u0004\u0018\u00010@2\u0006\u0010D\u001a\u0002052\u0006\u0010E\u001a\u000205H\u0016J\u0010\u0010F\u001a\u00020<2\u0006\u0010G\u001a\u000208H\u0002J(\u0010H\u001a\u00020<2\u0006\u0010I\u001a\u0002082\u0006\u0010J\u001a\u0002082\u0006\u0010K\u001a\u0002082\u0006\u0010L\u001a\u000208H\u0002J\u0010\u0010M\u001a\u00020<2\u0006\u0010 \u001a\u00020NH\u0002J\b\u0010O\u001a\u00020<H\u0002J\b\u0010P\u001a\u00020<H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\u00020\u00108\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\"\u001a\u00020#8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010\'R\u001e\u0010(\u001a\u00020)8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0/0\u001eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006R"}, d2 = {"Lkr/ac/tukorea/whereareu/util/location/LocationService;", "Landroid/app/Service;", "()V", "accelerometerSensor", "Lkr/ac/tukorea/whereareu/util/sensor/AccelerometerSensor;", "getAccelerometerSensor", "()Lkr/ac/tukorea/whereareu/util/sensor/AccelerometerSensor;", "setAccelerometerSensor", "(Lkr/ac/tukorea/whereareu/util/sensor/AccelerometerSensor;)V", "gyroScopeSensor", "Lkr/ac/tukorea/whereareu/util/sensor/GyroScopeSensor;", "getGyroScopeSensor", "()Lkr/ac/tukorea/whereareu/util/sensor/GyroScopeSensor;", "setGyroScopeSensor", "(Lkr/ac/tukorea/whereareu/util/sensor/GyroScopeSensor;)V", "lightSensor", "Lkr/ac/tukorea/whereareu/util/sensor/LightSensor;", "getLightSensor", "()Lkr/ac/tukorea/whereareu/util/sensor/LightSensor;", "setLightSensor", "(Lkr/ac/tukorea/whereareu/util/sensor/LightSensor;)V", "localBroadcastManager", "Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "getLocalBroadcastManager", "()Landroidx/localbroadcastmanager/content/LocalBroadcastManager;", "localBroadcastManager$delegate", "Lkotlin/Lazy;", "locationClient", "Lkr/ac/tukorea/whereareu/util/location/LocationClient;", "locationExtraInfo", "", "", "locationInfo", "", "magneticFieldSensor", "Lkr/ac/tukorea/whereareu/util/sensor/MagneticFieldSensor;", "getMagneticFieldSensor", "()Lkr/ac/tukorea/whereareu/util/sensor/MagneticFieldSensor;", "setMagneticFieldSensor", "(Lkr/ac/tukorea/whereareu/util/sensor/MagneticFieldSensor;)V", "repository", "Lkr/ac/tukorea/whereareu/data/repository/dementia/home/DementiaHomeRepositoryImpl;", "getRepository", "()Lkr/ac/tukorea/whereareu/data/repository/dementia/home/DementiaHomeRepositoryImpl;", "setRepository", "(Lkr/ac/tukorea/whereareu/data/repository/dementia/home/DementiaHomeRepositoryImpl;)V", "sensorValueList", "", "serviceScope", "Lkotlinx/coroutines/CoroutineScope;", "checkReadyToPost", "", "getBatteryPercent", "", "()Ljava/lang/Integer;", "getCurrentTime", "", "getDementiaKey", "getRingMode", "initSensor", "", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "flags", "startId", "postLocationInfo", "dementiaKey", "saveFile", "date", "time", "userState", "isError", "sendLocation", "Lkr/ac/tukorea/whereareu/data/model/home/LocationInfo;", "start", "stop", "Companion", "app_debug"})
public final class LocationService extends android.app.Service {
    @javax.inject.Inject()
    public kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl repository;
    @javax.inject.Inject()
    public kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor accelerometerSensor;
    @javax.inject.Inject()
    public kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor magneticFieldSensor;
    @javax.inject.Inject()
    public kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor gyroScopeSensor;
    @javax.inject.Inject()
    public kr.ac.tukorea.whereareu.util.sensor.LightSensor lightSensor;
    private kr.ac.tukorea.whereareu.util.location.LocationClient locationClient;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope serviceScope = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<java.util.List<java.lang.Float>> sensorValueList;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Double> locationInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.Float> locationExtraInfo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy localBroadcastManager$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START = "ACTION_START";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP = "ACTION_STOP";
    public static final int LIGHT_SENSOR = 0;
    public static final int GYRO_SENSOR = 1;
    public static final int ACCELEROMETER_SENSOR = 2;
    public static final int MAGNETIC_SENSOR = 3;
    public static final int LATITUDE = 0;
    public static final int LONGITUDE = 1;
    public static final int SPEED = 0;
    public static final int BEARING = 1;
    public static final int DATE = 0;
    public static final int TIME = 1;
    @org.jetbrains.annotations.NotNull()
    public static final kr.ac.tukorea.whereareu.util.location.LocationService.Companion Companion = null;
    
    public LocationService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl getRepository() {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.data.repository.dementia.home.DementiaHomeRepositoryImpl p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor getAccelerometerSensor() {
        return null;
    }
    
    public final void setAccelerometerSensor(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor getMagneticFieldSensor() {
        return null;
    }
    
    public final void setMagneticFieldSensor(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor getGyroScopeSensor() {
        return null;
    }
    
    public final void setGyroScopeSensor(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.sensor.LightSensor getLightSensor() {
        return null;
    }
    
    public final void setLightSensor(@org.jetbrains.annotations.NotNull()
    kr.ac.tukorea.whereareu.util.sensor.LightSensor p0) {
    }
    
    private final androidx.localbroadcastmanager.content.LocalBroadcastManager getLocalBroadcastManager() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent) {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    private final void initSensor() {
    }
    
    private final void postLocationInfo(java.lang.String dementiaKey) {
    }
    
    private final void saveFile(java.lang.String date, java.lang.String time, java.lang.String userState, java.lang.String isError) {
    }
    
    private final java.lang.String getDementiaKey() {
        return null;
    }
    
    private final boolean checkReadyToPost() {
        return false;
    }
    
    @android.annotation.SuppressLint(value = {"SimpleDateFormat"})
    private final java.util.List<java.lang.String> getCurrentTime() {
        return null;
    }
    
    private final java.lang.Integer getBatteryPercent() {
        return null;
    }
    
    private final int getRingMode() {
        return 0;
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void start() {
    }
    
    private final void sendLocation(kr.ac.tukorea.whereareu.data.model.home.LocationInfo locationInfo) {
    }
    
    private final void stop() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lkr/ac/tukorea/whereareu/util/location/LocationService$Companion;", "", "()V", "ACCELEROMETER_SENSOR", "", "ACTION_START", "", "ACTION_STOP", "BEARING", "DATE", "GYRO_SENSOR", "LATITUDE", "LIGHT_SENSOR", "LONGITUDE", "MAGNETIC_SENSOR", "SPEED", "TIME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}