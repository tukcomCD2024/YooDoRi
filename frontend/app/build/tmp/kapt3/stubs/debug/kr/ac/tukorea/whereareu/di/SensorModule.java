package kr.ac.tukorea.whereareu.di;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor;
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor;
import kr.ac.tukorea.whereareu.util.sensor.LightSensor;
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor;
import javax.inject.Singleton;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\r"}, d2 = {"Lkr/ac/tukorea/whereareu/di/SensorModule;", "", "()V", "provideAccelerometer", "Lkr/ac/tukorea/whereareu/util/sensor/AccelerometerSensor;", "app", "Landroid/app/Application;", "provideGyroScopeSensor", "Lkr/ac/tukorea/whereareu/util/sensor/GyroScopeSensor;", "provideLightSensor", "Lkr/ac/tukorea/whereareu/util/sensor/LightSensor;", "provideMagneticFieldSensor", "Lkr/ac/tukorea/whereareu/util/sensor/MagneticFieldSensor;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class SensorModule {
    @org.jetbrains.annotations.NotNull()
    public static final kr.ac.tukorea.whereareu.di.SensorModule INSTANCE = null;
    
    private SensorModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.sensor.LightSensor provideLightSensor(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor provideAccelerometer(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor provideGyroScopeSensor(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor provideMagneticFieldSensor(@org.jetbrains.annotations.NotNull()
    android.app.Application app) {
        return null;
    }
}