package kr.ac.tukorea.whereareu.presentation.login

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentNokOtpBinding
import kr.ac.tukorea.whereareu.databinding.FragmentPatientOtpBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

class PatientOtpFragment: BaseFragment<FragmentPatientOtpBinding>(R.layout.fragment_patient_otp) {

    override fun initObserver() {

    }

    override fun initView() {
        binding.view = this
    }

    fun onClickBackBtn(){
        findNavController().popBackStack()
    }
}