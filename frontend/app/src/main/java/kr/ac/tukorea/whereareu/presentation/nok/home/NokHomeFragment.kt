package kr.ac.tukorea.whereareu.presentation.nok.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentHomeBinding
import kr.ac.tukorea.whereareu.domain.home.MeaningfulPlaceInfo
import kr.ac.tukorea.whereareu.firebase.FCMService
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.login.nok.NokIdentityFragmentDirections
import kr.ac.tukorea.whereareu.presentation.nok.home.adapter.MeaningfulPlaceRVA
import kr.ac.tukorea.whereareu.util.extension.repeatOnStarted


@AndroidEntryPoint
class NokHomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),
    MeaningfulPlaceRVA.MeaningfulPlaceRVAClickListener {
    private val viewModel: NokHomeViewModel by activityViewModels()

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001
    private val meaningfulPlaceRVA by lazy {
        MeaningfulPlaceRVA()
    }
    private val navigator: NavController by lazy {
        findNavController()
    }
    override fun initObserver() {
        repeatOnStarted {
            viewModel.predictEvent.collect { predictEvent ->
                handlePredictEvent(predictEvent)
            }
        }

        repeatOnStarted {
            viewModel.meaningfulPlace.collect{
                meaningfulPlaceRVA.submitList(it)
            }
        }
    }

    private fun handlePredictEvent(event: NokHomeViewModel.PredictEvent){
        when(event){
            is NokHomeViewModel.PredictEvent.StartPredict -> {
                initMeaningfulListRVA()
            }

            is NokHomeViewModel.PredictEvent.PredictLocation -> {
                with(event.predictLocation){
                    val address = meaningfulPlaceInfo.address
                    meaningfulPlaceInfo.latLng
                    binding.addressTv.text = address
                    val meaningfulPlaceInfo = MeaningfulPlaceInfo(address, emptyList(), meaningfulPlaceInfo.latLng, false, policeStationInfo)

                    binding.mapViewBtn.setOnClickListener {
                        Log.d("mapViewBtn", "clicked")
                        viewModel.eventPredict(NokHomeViewModel.PredictEvent.MapView(BottomSheetBehavior.STATE_COLLAPSED, meaningfulPlaceInfo.latLng))
                    }

                    binding.infoViewBtn.setOnClickListener {
                        val action = NokHomeFragmentDirections.actionNokHomeFragmentToMeaningfulPlaceDetailFragment(meaningfulPlaceInfo)
                        navigator.navigate(action)
                    }
                }
            }
            else -> {}
        }
    }

    override fun initView() {
        FCMService().getFirebaseToken()

        binding.view = this
        binding.viewModel = viewModel
        viewModel.fetchSafeAreaAll()
        checkLocationPermission()
    }

    private fun initMeaningfulListRVA(){
        binding.rv.adapter = meaningfulPlaceRVA
        meaningfulPlaceRVA.setRVAClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.eventMeaningfulPlace()
        viewModel.eventPredictLocation()
        initMeaningfulListRVA()
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


    // inner RVA 클릭 이벤트
    override fun onClickMapView(latLng: LatLng) {
        viewModel.eventPredict(NokHomeViewModel.PredictEvent.MapView(BottomSheetBehavior.STATE_COLLAPSED, latLng))
    }

    override fun onClickInfoView(meaningfulPlace: MeaningfulPlaceInfo) {
        val action = NokHomeFragmentDirections.actionNokHomeFragmentToMeaningfulPlaceDetailFragment(
            meaningfulPlace
        )
        navigator.navigate(action)
    }
}