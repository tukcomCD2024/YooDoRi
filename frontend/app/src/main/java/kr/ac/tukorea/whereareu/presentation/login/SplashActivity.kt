package kr.ac.tukorea.whereareu.presentation.login

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R

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
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }, 1000)

//        when{
//            isDementia -> {
//                viewModel.sendDementiaIdentity(
//                    DementiaIdentity(
//                        spf.getString("name", ""),
//                        spf.getString("phone", ""),
//                    )
//                )
//            }
//            isNok -> {
//                viewModel.sendNokIdentity(
//                    NokIdentity(
//                        spf.getString("key", ""),
//                        spf.getString("name", ""),
//                        spf.getString("phone", ""),
//                    )
//                )
//            }
//            else -> {
//                window.setFlags(
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//                )
//                Handler(Looper.getMainLooper()).postDelayed({
//                    val intent = Intent(this, LoginActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                    startActivity(intent)
//                }, 1000)
//            }
//        }

//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_splash)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
//        Handler(Looper.getMainLooper()).postDelayed({
//            val intent = Intent(this, LoginActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//            startActivity(intent)
//        }, 1000)
    }
}