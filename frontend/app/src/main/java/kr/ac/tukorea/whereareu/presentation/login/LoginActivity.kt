package kr.ac.tukorea.whereareu.presentation.login

import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.ActivityLoginBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseActivity
import kr.ac.tukorea.whereareu.util.extension.setStatusBarTransparent
import kr.ac.tukorea.whereareu.util.extension.statusBarHeight

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    override fun initView() {
        //상태바 투명 설정
        this.setStatusBarTransparent()
        binding.layout.setPadding(0, this.statusBarHeight(), 0, 0)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    override fun initObserver() {
    }
    // extention.kt
}