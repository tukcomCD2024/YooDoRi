package kr.ac.tukorea.whereareu.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor
import kr.ac.tukorea.whereareu.util.sensor.LightSensor
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    @Provides
    @Singleton
    fun provideLightSensor(app: Application): LightSensor {
        return LightSensor(app)
    }

    @Provides
    @Singleton
    fun provideAccelerometer(app: Application): AccelerometerSensor {
        return AccelerometerSensor(app)
    }

    @Provides
    @Singleton
    fun provideGyroScopeSensor(app: Application): GyroScopeSensor {
        return GyroScopeSensor(app)
    }

    @Provides
    @Singleton
    fun provideMagneticFieldSensor(app: Application): MagneticFieldSensor {
        return MagneticFieldSensor(app)
    }
}