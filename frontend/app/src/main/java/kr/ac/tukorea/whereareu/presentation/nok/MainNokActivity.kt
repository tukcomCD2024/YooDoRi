package kr.ac.tukorea.whereareu.presentation.nok

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.ActivityNokMainBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity
import kotlin.Int

@AndroidEntryPoint
class MainNokActivity : BaseActivity<ActivityNokMainBinding>(R.layout.activity_nok_main) {
    override fun initView() {
        initNavigator()
        binding.mainLayout.setPadding(0,0, 0, 0)
    }

    private fun initNavigator(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)
    }

    override fun initObserver() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessageReceiver, IntentFilter("gps")
        )
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

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "destroy")
    }

    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val location = intent?.getDoubleArrayExtra("location", )
            //val long = intent?.getDoubleExtra("long", 0.0)
            Log.d("location log", "${location?.get(0)}, ${location?.get(1)}")
            // Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }
}