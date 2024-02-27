package kr.ac.tukorea.whereareu.presentation.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Insets.add
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService
import androidx.core.location.LocationManagerCompat.requestLocationUpdates
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.gms.location.LocationServices
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.LocationInfo
import kr.ac.tukorea.whereareu.data.model.sensor.Accelerometer
import kr.ac.tukorea.whereareu.databinding.FragmentHomeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.util.LocationService
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.Flow
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    OnMapReadyCallback, LocationListener {
    /*private val sensorManager: SensorManager by lazy {
        requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }*/
    private val viewModel: HomeViewModel by viewModels()
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireActivity())
    }
    private lateinit var locationSource: FusedLocationSource
    private lateinit var naverMap: NaverMap
    private val gpsManager by lazy {
        requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    //private var axisFlow = kotlinx.coroutines.flow.Flow<List<Accelerometer>>
    private var axisList = mutableListOf<Accelerometer>()
    private val _axisListFlow = MutableSharedFlow<List<Accelerometer>>()
    private var locationList = mutableListOf<kr.ac.tukorea.whereareu.data.model.Location>()

    //private var axisFlow = flowOf()
    override fun initObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                _axisListFlow.collect {
                    Log.d("axis collect", it.toString())
                }
            }
        }
    }

    override fun initView() {
        viewModel.init
        checkLocationPermission()
        gpsManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 1000, 10f, this
        )
        //makeDummy()

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
        val resourceId: Int =
            context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId > 0) {
            context.resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
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
                    locationList.add(kr.ac.tukorea.whereareu.data.model.Location(latitude, longitude))
                    increaseLocation(location)
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

    override fun onLocationChanged(location: Location) {
        val latitude = location.latitude
        val longitude = location.longitude
        // 위치 정보 사용
        Log.d("Location", "Latitude: $latitude, Longitude: $longitude")
    }

    private fun increaseLocation(location: Location){
        lifecycleScope.launch{
            while (true){
                locationList.add(kr.ac.tukorea.whereareu.data.model.Location(location.latitude++, location.longitude++))
                delay(1000)
                //Log.d("loca list", locationList.toString())
            }
        }
    }

    private fun makeDummy(){
        lifecycleScope.launch{
            while (true){
                delay(60000)
                val axislast = axisList.last()
                val locaLast = locationList.last()
                Log.d("last", "$axislast, $locaLast")
                val currentDate = Date(System.currentTimeMillis())
                val sdf = SimpleDateFormat("YYYY-MM-DD")
                val date = sdf.format(currentDate)
                Log.d("date", date.toString())
                viewModel.postLocationInfo(LocationInfo(
                    "227609",
                    locaLast.latitude,
                    locaLast.longitude,
                    "지금",
                    date,
                    0f,
                    axislast.xAxis,
                    axislast.yAxis,
                    axislast.zAxis,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    0f,
                    80.0f,
                    true,
                    true,
                    true
                ))
                delay(5000)
            }
        }
    }
}