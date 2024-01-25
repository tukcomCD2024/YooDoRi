package kr.ac.tukorea.whereareu.presentation.login

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentLoginBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

class LoginFragment: BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    override fun initObserver() {

    }

    override fun initView() {
        binding.view = this
    }

    fun onClickNok(){
        findNavController().navigate(R.id.action_loginFragment_to_nokIdentityFragment)
    }


}