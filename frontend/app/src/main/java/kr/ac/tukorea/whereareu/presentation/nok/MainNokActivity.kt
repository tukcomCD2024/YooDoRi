package kr.ac.tukorea.whereareu.presentation.nok

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.ActivityNokMainBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity
import kr.ac.tukorea.whereareu.presentation.home.SensorWorker
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainNokActivity : BaseActivity<ActivityNokMainBinding>(R.layout.activity_nok_main) {
    override fun initView() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)
        binding.mainLayout.setPadding(0,0, 0, 0)

        val periodicWorkRequest = PeriodicWorkRequestBuilder<SensorWorker>(15, TimeUnit.MINUTES)
            .build()
        val workManager = WorkManager.getInstance(this)
            .enqueueUniquePeriodicWork("test",
                ExistingPeriodicWorkPolicy.REPLACE,
                periodicWorkRequest)
    }

    override fun initObserver() {

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
}