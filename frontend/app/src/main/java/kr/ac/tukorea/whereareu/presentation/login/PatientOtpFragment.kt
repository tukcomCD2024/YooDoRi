package kr.ac.tukorea.whereareu.presentation.login

import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentPatientOtpBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

class PatientOtpFragment: BaseFragment<FragmentPatientOtpBinding>(R.layout.fragment_patient_otp) {
    private lateinit var viewModel: LoginViewModel

    override fun initObserver() {
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.dementiaOtp.observe(this){
            binding.displayOtpTv.text = it
        }
    }

    override fun initView() {
        binding.view = this
    }

    fun onClickBackBtn(){
        findNavController().popBackStack()
    }
}