package kr.ac.tukorea.whereareu.presentation.dementia

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.ActivityDementiaMainBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity
import kr.ac.tukorea.whereareu.util.LocationService

@AndroidEntryPoint
class MainDementiaActivity: BaseActivity<ActivityDementiaMainBinding>(R.layout.activity_dementia_main) {
    override fun initView() {
        initNavigator()
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

    private fun initLocationService(){
        Intent(applicationContext, LocationService::class.java).apply {
            action = LocationService.ACTION_START
            startService(this)
        }
    }

    private fun requestSystemGPSEnable() {
        Toast.makeText(this, "핸드폰 GPS를 켜주세요", Toast.LENGTH_SHORT).show()
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