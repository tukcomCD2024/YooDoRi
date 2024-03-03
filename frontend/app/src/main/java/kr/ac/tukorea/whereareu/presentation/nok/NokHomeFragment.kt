package kr.ac.tukorea.whereareu.presentation.nok

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentHomeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.home.HomeViewModel
import kr.ac.tukorea.whereareu.presentation.home.NokHomeViewModel
import kr.ac.tukorea.whereareu.util.LoginUtil.repeatOnStarted

@AndroidEntryPoint
class NokHomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    OnMapReadyCallback {
    private val viewModel: NokHomeViewModel by activityViewModels()
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private var naverMap: NaverMap? = null

    override fun initObserver() {
        repeatOnStarted {
            viewModel.dementiaLocation.collect{ response ->
                with(binding){
                    stateTv.text = updateDementiaStatus(response.userStatus)

                }
                naverMap?.let {
                    val coord = LatLng(response.latitude, response.longitude)
                    //val coord = LatLng(location)

                    val locationOverlay = it.locationOverlay
                    locationOverlay.isVisible = true
                    locationOverlay.position = coord
                    //locationOverlay.bearing = location.bearing

                    it.moveCamera(CameraUpdate.scrollTo(coord))
                }
            }
        }
    }

    override fun initView() {
        checkLocationPermission()
        updateDementiaName()
        initMap()
    }

    private fun updateDementiaName(){
        val spf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
        val dementiaName = spf.getString("name", "")
        if (!dementiaName.isNullOrBlank()){
            binding.dementiaNameTv.text = dementiaName
        }
    }

    private fun updateDementiaStatus(status: Int): String{
        return when(status){
            0 -> "정지"
            1 -> "도보"
            2 -> "차량"
            3 -> "지하철"
            else -> "알수없음"
        }
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
        mapFragment.getMapAsync { map ->
            naverMap = map
        }
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
                } else {
                    // 권한이 거부된 경우 처리 (예: 사용자에게 권한이 필요하다고 알리기)
                }
            }
        }
    }

    override fun onMapReady(p0: NaverMap) {

    }
}