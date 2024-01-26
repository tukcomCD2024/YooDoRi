package kr.ac.tukorea.whereareu.presentation.login

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentNokIdentityBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

class NokIdentityFragment: BaseFragment<FragmentNokIdentityBinding>(R.layout.fragment_nok_identity) {

    override fun initObserver() {

    }

    override fun initView() {
        binding.view = this
    }

    fun onClickBackBtn(){
        findNavController().popBackStack()
    }

    fun onClickInputDone(){
        findNavController().navigate(R.id.action_nokIdentityFragment_to_nokOtpFragment)
    }
}