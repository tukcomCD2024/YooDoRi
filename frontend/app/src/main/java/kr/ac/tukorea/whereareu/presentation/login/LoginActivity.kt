package kr.ac.tukorea.whereareu.presentation.login

import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.ActivityLoginBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    //LoginFragment에서 popBackStack으로 뒤로가기 방지
    private val callback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
                navController.popBackStack()
            Log.d("current", navController.currentBackStackEntry.toString())
            if(navController.currentBackStackEntry == null){
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        this.onBackPressedDispatcher.addCallback(this, callback)
    }
}