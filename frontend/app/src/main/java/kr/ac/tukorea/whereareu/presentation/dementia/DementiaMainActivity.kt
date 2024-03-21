package kr.ac.tukorea.whereareu.presentation.dementia

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.ActivityDementiaMainBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity
import kr.ac.tukorea.whereareu.util.location.LocationService
import kr.ac.tukorea.whereareu.util.extension.navigationHeight
import kr.ac.tukorea.whereareu.util.extension.setStatusBarTransparent
import kr.ac.tukorea.whereareu.util.extension.showToastLong
import kr.ac.tukorea.whereareu.util.extension.showToastOnView
import kr.ac.tukorea.whereareu.util.extension.showToastShort


@AndroidEntryPoint
class DementiaMainActivity: BaseActivity<ActivityDementiaMainBinding>(R.layout.activity_dementia_main) {
    private val BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE = 456
    override fun initView() {
        initNavigator()
        this.setStatusBarTransparent()
        binding.layout.setPadding(0, 0, 0, this.navigationHeight())

        if (!checkBackGroundLocationPermission()){
            lifecycleScope.launch {
                showToastLong(this@DementiaMainActivity, "백그라운드 위치 정보 수집을 위해\n권한을 항상 허용으로 설정해주세요")
                delay(4000)
                requestBackGroundLocationPermission()
            }
        }

        //gps off시 gps 설정화면으로 이동
        checkGpsStatus()

        initLocationService()
    }

    private fun initNavigator(){
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)
    }

    private fun checkBackGroundLocationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            (ContextCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED)
        } else {
            return true
        }
    }

    private fun requestBackGroundLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                BACKGROUND_LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun initLocationService(){
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }

    private fun requestSystemGPSEnable() {
        showToastShort(this, "핸드폰 GPS를 켜주세요")
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        startActivity(intent)
    }

    private fun checkGpsStatus(){
        val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER)
        Log.d("gps status", isGpsEnable.toString())
        if (!isGpsEnable){
            requestSystemGPSEnable()
        }
    }

    override fun initObserver() {
    }

}