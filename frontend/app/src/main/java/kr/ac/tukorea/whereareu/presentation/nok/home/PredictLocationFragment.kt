package kr.ac.tukorea.whereareu.presentation.nok.home

import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentPredictLocationBinding
import kr.ac.tukorea.whereareu.databinding.FragmentSafeAreaBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

@AndroidEntryPoint
class PredictLocationFragment: BaseFragment<FragmentPredictLocationBinding>(R.layout.fragment_predict_location),
    OnMapReadyCallback {
    private val viewModel: NokHomeViewModel by activityViewModels()
    private var naverMap: NaverMap? = null
    private val navigator by lazy {
        findNavController()
    }

    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            Log.d("back press", "back press")
            viewModel.setIsPredicted(false)
            navigator.popBackStack()
        }
    }
    override fun initObserver() {

    }

    override fun initView() {
        Log.d("destination", navigator.currentDestination?.id.toString())
        initMap()
        requireActivity().onBackPressedDispatcher.addCallback(callback)
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

    override fun onMapReady(p0: NaverMap) {

    }
}