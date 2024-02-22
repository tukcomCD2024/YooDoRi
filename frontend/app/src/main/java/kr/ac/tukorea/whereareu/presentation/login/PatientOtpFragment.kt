package kr.ac.tukorea.whereareu.presentation.login

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.login.CheckConnect
import kr.ac.tukorea.whereareu.databinding.FragmentPatientOtpBinding
import kr.ac.tukorea.whereareu.presentation.MainActivity
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.util.LoginUtil.repeatOnStarted

class PatientOtpFragment : BaseFragment<FragmentPatientOtpBinding>(R.layout.fragment_patient_otp) {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun initObserver() {
        binding.viewModel = viewModel

        repeatOnStarted {
            viewModel.eventFlow.collect {
                handleEvent(it)
            }
        }

        /*viewModel.isConnect.observe(this){
            if(it == "success"){
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            else{
                Toast.makeText(requireContext(), "아직 보호자와 연결이 되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }*/
    }

    override fun initView() {
        binding.view = this
    }
    fun onClickBackBtn() {
        findNavController().popBackStack()
    }

    fun onClickInputDone() {
        viewModel.checkConnected(CheckConnect(binding.displayOtpTv.text.toString()))
    }

    private fun handleEvent(event: LoginViewModel.Event) {
        when (event) {
            LoginViewModel.Event.Fail -> {
                Toast.makeText(
                    requireContext(),
                    "아직 보호자와 연결이 되지 않았습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}