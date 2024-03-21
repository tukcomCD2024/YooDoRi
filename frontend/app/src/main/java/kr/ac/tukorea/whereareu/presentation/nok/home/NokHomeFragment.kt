package kr.ac.tukorea.whereareu.presentation.nok.home

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PointF
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.OverlayImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.home.GetLocationInfoResponse
import kr.ac.tukorea.whereareu.databinding.IconLocationOverlayLayoutBinding
import kr.ac.tukorea.whereareu.domain.home.MeaningfulPlace
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.util.extension.repeatOnStarted
import kotlin.math.roundToInt

@AndroidEntryPoint
class NokHomeFragment : BaseFragment<kr.ac.tukorea.whereareu.databinding.FragmentHomeBinding>(R.layout.fragment_home),
    OnMapReadyCallback {
    private val viewModel: NokHomeViewModel by activityViewModels()
    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private var naverMap: NaverMap? = null
    private var dementiaName: String? = null
    private val navigator by lazy {
        findNavController()
    }
    private val meaningfulListRVA by lazy {
        MeaningfulListRVA()
    }
    private lateinit var behavior: BottomSheetBehavior<ConstraintLayout>

    override fun initObserver() {
    }

    private fun updateDementiaMovementStatus(status: Int): String{
        return when(status){
            1 -> "정지"
            2 -> "도보"
            3 -> "차량"
            4 -> "지하철"
            else -> "알수없음"
        }
    }

    private fun updateDementiaStatus(dementiaStatus: GetLocationInfoResponse){
        with(binding){
            stateTv.text = updateDementiaMovementStatus(dementiaStatus.userStatus)
            batteryTv.text = "${dementiaStatus.battery}%"
            if (dementiaStatus.isInternetOn){
                internetStatusTv.text = "on"
                wifiIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_wifi_on))
            } else {
                internetStatusTv.text = "off"
                wifiIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_wifi_off))
            }

            if (dementiaStatus.isGpsOn){
                gpsStatusTv.text = "on"
                gpsIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_gps_on_24))
            } else {
                gpsStatusTv.text = "off"
                gpsIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_gps_off_24))
            }

            when(dementiaStatus.isRingstoneOn){
                0 -> {
                    ringModeTv.text = "무음"
                    ringModeIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_bell_off_24))
                }

                1 -> {
                    ringModeTv.text = "진동"
                    ringModeIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_vibrate_24))
                }

                2 -> {
                    ringModeTv.text = "벨소리"
                    ringModeIv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_bell_24))
                }

                else -> {
                    ringModeTv.text = "알수없음"
                }
            }
        }
    }

    private fun trackingDementiaLocation(coord: LatLng, bearing: Float, name: String, speed: Float){
        naverMap?.let {
            val locationOverlay = it.locationOverlay
            locationOverlay.isVisible = true
            val iconBinding = IconLocationOverlayLayoutBinding.inflate(layoutInflater)
            iconBinding.nameTv.text = name

            // m/s to km/h
            iconBinding.speedTv.text = (speed * 3.6).roundToInt().toString()
            val speedTv = iconBinding.layout
            locationOverlay.icon = OverlayImage.fromView(speedTv)
            locationOverlay.circleRadius = 0
            locationOverlay.position = coord
            //locationOverlay.bearing = bearing
            locationOverlay.anchor = PointF(0.5f, 1f)

            it.moveCamera(CameraUpdate.scrollTo(coord))
        }
    }

    override fun initView() {
        checkLocationPermission()
        updateDementiaName()
        initMap()
        goToPredictLocationFragment()
    }

    private fun goToPredictLocationFragment(){
        binding.predictTv.setOnClickListener {
            viewModel.setIsPredicted(true)
            binding.homeGroup.visibility = View.GONE
            binding.bottomSheet.visibility = View.VISIBLE
            initBottomSheet()
            initMeaningfulListRVA()
            //navigator.navigate(R.id.action_nokHomeFragment_to_predictLocationFragment)
        }
    }

    private fun initMeaningfulListRVA(){
        binding.rv.adapter = meaningfulListRVA
        binding.rv.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        val list = listOf<MeaningfulPlace>(
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
            MeaningfulPlace("한국공학대학교", "시흥시 뭐시기"),
        )
        meaningfulListRVA.submitList(list)
    }

    private fun initBottomSheet(){
        behavior = BottomSheetBehavior.from(binding.bottomSheet)
        behavior.state = BottomSheetBehavior.STATE_COLLAPSED
        behavior.peekHeight = 20
        behavior.isFitToContents = false
        behavior.halfExpandedRatio = 0.4f
        behavior.expandedOffset = 100


        // half expanded state일 때 접기 제어
        behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
            var isHalfExpanded = false
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when(newState){
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        isHalfExpanded = true
                    }
                    BottomSheetBehavior.STATE_COLLAPSED and BottomSheetBehavior.STATE_HALF_EXPANDED-> {
                        isHalfExpanded = false
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                if(isHalfExpanded && slideOffset < 0.351f){
                    behavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

        })
    }

    private fun updateDementiaName(){
        val spf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
        dementiaName = spf.getString("name", "")
        if (!dementiaName.isNullOrBlank()){
            binding.dementiaNameTv.text = dementiaName
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

    override fun onResume() {
        super.onResume()
        Log.d("resume", "resume")
        repeatOnStarted {
            viewModel.dementiaLocation.collect{ response ->
                Log.d("response", response.toString())
                updateDementiaStatus(response)
                val coord = LatLng(response.latitude, response.longitude)
                trackingDementiaLocation(coord, response.bearing, dementiaName?:"", response.currentSpeed)
            }
        }
    }

    override fun onMapReady(p0: NaverMap) {

    }
}