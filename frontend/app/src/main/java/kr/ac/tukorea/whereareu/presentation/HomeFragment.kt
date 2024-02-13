package kr.ac.tukorea.whereareu.presentation

import android.content.Context
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentHomeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    override fun initObserver() {

    }

    override fun initView() {
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
}