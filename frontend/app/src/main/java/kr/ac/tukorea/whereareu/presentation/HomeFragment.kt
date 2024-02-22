package kr.ac.tukorea.whereareu.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.sensor.Accelerometer
import kr.ac.tukorea.whereareu.databinding.FragmentHomeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), SensorEventListener {
    /*private val sensorManager: SensorManager by lazy {
        requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }*/
    private lateinit var sensorManager: SensorManager
    private var accSeonsor: Sensor? = null
    private var lastAxis = Accelerometer()
    override fun initObserver() {

    }

    override fun initView() {
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accSeonsor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (accSeonsor != null) {
            sensorManager.registerListener(this, accSeonsor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {
            // 가속도 센서가 없는 경우에 대한 처리
        }
        //binding.homeFragment.setPadding(0,getStatusBarHeight(requireContext()), 0, getNaviBarHeight(requireContext()))
    }

    fun getStatusBarHeight(context: Context): Int {
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")

        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

    fun getNaviBarHeight(context: Context): Int {
        val resourceId: Int = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event?.sensor == accSeonsor) {
            val xAxis = event.values[0]
            val yAxis = event.values[1]
            val zAxis = event.values[2]
            val axis = Accelerometer(xAxis, yAxis, zAxis)
            if(axis != lastAxis){
                Log.d("sensor", "$xAxis, $yAxis, $zAxis")
                lastAxis = axis
            }
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
}