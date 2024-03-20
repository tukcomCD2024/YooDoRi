package kr.ac.tukorea.whereareu.presentation.nok.home

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentSafeAreaBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

class PredictLocationFragment: BaseFragment<FragmentSafeAreaBinding>(R.layout.fragment_safe_area),
    OnMapReadyCallback {
    private val viewModel: NokHomeViewModel by viewModels()
    private var naverMap: NaverMap? = null
    private val navigator by lazy {
        findNavController()
    }
    override fun initObserver() {

    }

    override fun initView() {
        Log.d("destination", navigator.currentDestination?.id.toString())
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