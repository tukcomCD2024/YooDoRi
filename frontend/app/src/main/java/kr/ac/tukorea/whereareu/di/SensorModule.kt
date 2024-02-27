package kr.ac.tukorea.whereareu.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kr.ac.tukorea.whereareu.util.AccelerometerSensor
import kr.ac.tukorea.whereareu.util.GyroScopeSensor
import kr.ac.tukorea.whereareu.util.LightSensor
import kr.ac.tukorea.whereareu.util.MagneticFieldSensor
import kr.ac.tukorea.whereareu.util.MeasurableSensor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SensorModule {

    @Provides
    @Singleton
    fun provideLightSensor(app: Application): LightSensor{
        return LightSensor(app)
    }

    @Provides
    @Singleton
    fun provideAccelerometer(app: Application): AccelerometerSensor{
        return AccelerometerSensor(app)
    }

    @Provides
    @Singleton
    fun provideGyroScopeSensor(app: Application): GyroScopeSensor{
        return GyroScopeSensor(app)
    }

    @Provides
    @Singleton
    fun provideMagneticFieldSensor(app: Application): MagneticFieldSensor{
        return MagneticFieldSensor(app)
    }
}