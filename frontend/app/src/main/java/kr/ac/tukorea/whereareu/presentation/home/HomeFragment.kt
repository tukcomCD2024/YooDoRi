package kr.ac.tukorea.whereareu.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Insets.add
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.location.LocationManagerCompat.requestLocationUpdates
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.gms.location.LocationServices
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.sensor.Accelerometer
import kr.ac.tukorea.whereareu.databinding.FragmentHomeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), SensorEventListener,
    OnMapReadyCallback {
    /*private val sensorManager: SensorManager by lazy {
        requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }*/
    private val sensorManager: SensorManager by lazy {
        requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    private var accSeonsor: Sensor? = null
    private var lastAxis = Accelerometer()
    private var gyroSensor: Sensor? = null
    private var sensorList = mutableListOf<Sensor>()
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    override fun initObserver() {

    }

    override fun initView() {
        checkLocationPermission()
        initSensorManager()
        accSeonsor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        //gyroSensor = sensorManager.getSensorList(Sensor)

        if (accSeonsor != null) {
            sensorManager.registerListener(this, accSeonsor, SensorManager.SENSOR_DELAY_NORMAL)
        } else {

        }

        //val fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

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

    private fun initSensorManager(){
        sensorList.apply {
            add(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!)
            add(sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!)
        }
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor == accSeonsor) {
            val xAxis = event.values[0]
            val yAxis = event.values[1]
            val zAxis = event.values[2]
            //Log.d("sensor", "$xAxis, $yAxis, $zAxis")
            val axis = Accelerometer(xAxis, yAxis, zAxis)
            /*if(axis != lastAxis){
                Log.d("sensor", "$xAxis, $yAxis, $zAxis")
                lastAxis = axis
            }*/
        }

    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    private fun initMap() {
        val fm = childFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

    }

    private fun checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // 권한이 이미 허용된 경우 위치 업데이트 요청
            requestLocationUpdates()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 위치 권한이 허용된 경우 위치 업데이트 요청
                    requestLocationUpdates()
                } else {
                    // 권한이 거부된 경우 처리 (예: 사용자에게 권한이 필요하다고 알리기)
                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                // 위치를 성공적으로 가져온 경우
                if (location != null) {
                    // location을 사용하여 현재 위치에 대한 작업 수행
                    val latitude = location.latitude
                    val longitude = location.longitude
                    Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
                }
            }
            .addOnFailureListener { e ->
                // 위치를 가져오지 못한 경우 처리
                // 예: Log.e("Location", "Failed to get location: $e")
            }
    }

    override fun onMapReady(p0: NaverMap) {

    }
}