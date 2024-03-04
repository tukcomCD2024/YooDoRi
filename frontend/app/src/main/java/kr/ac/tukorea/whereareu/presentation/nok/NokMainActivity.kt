package kr.ac.tukorea.whereareu.presentation.nok

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.ActivityNokMainBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity
import kr.ac.tukorea.whereareu.util.extension.navigationHeight
import kr.ac.tukorea.whereareu.util.extension.setStatusBarTransparent

@AndroidEntryPoint
class NokMainActivity : BaseActivity<ActivityNokMainBinding>(R.layout.activity_nok_main) {
    private val viewModel: NokHomeViewModel by viewModels()
    override fun initView() {
        //상태바 투명 설정
        this.setStatusBarTransparent()
        binding.layout.setPadding(0, 0, 0, this.navigationHeight())
        initNavigator()
        getDementiaLocation()
    }

    private fun initNavigator(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)
    }

    private fun getDementiaLocation(){
        val spf = getSharedPreferences("OtherUser", MODE_PRIVATE)
        val dementiaKey = spf.getString("key", "")
        if (!dementiaKey.isNullOrEmpty()) {
            lifecycleScope.launch {
                while (true) {
                    viewModel.getDementiaLocation(dementiaKey)
                    delay(60000)
                }
            }
        }
    }

    override fun initObserver() {
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mMessageReceiver, IntentFilter("gps")
        )
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