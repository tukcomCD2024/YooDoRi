package kr.ac.tukorea.whereareu.util.sensor

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import kr.ac.tukorea.whereareu.util.sensor.AndroidSensor

class AccelerometerSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_ACCELEROMETER,
    sensorType = Sensor.TYPE_ACCELEROMETER
)
class GyroScopeSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_GYROSCOPE,
    sensorType = Sensor.TYPE_GYROSCOPE
)

class MagneticFieldSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_COMPASS,
    sensorType = Sensor.TYPE_MAGNETIC_FIELD
)

class LightSensor(
    context: Context
): AndroidSensor(
    context = context,
    sensorFeature = PackageManager.FEATURE_SENSOR_LIGHT,
    sensorType = Sensor.TYPE_GYROSCOPE
)