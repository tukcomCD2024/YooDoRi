package kr.ac.tukorea.whereareu.presentation.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.presentation.dementia.DementiaMainActivity
import kr.ac.tukorea.whereareu.presentation.nok.NokMainActivity

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val spf: SharedPreferences = getSharedPreferences("User", MODE_PRIVATE)
        val isDementia = spf.getBoolean("isDementia", true)
        val isNok = spf.getBoolean("isNok", true)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val intent = if (spf.getString("name", "").isNullOrBlank()) {
            Intent(this, LoginActivity::class.java)
        } else {
            if (isDementia) {
                Intent(this, DementiaMainActivity::class.java)
            } else {
                Intent(this, NokMainActivity::class.java)
            }
        }
        lifecycleScope.launch {
            delay(1000)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}