package kr.ac.tukorea.whereareu.presentation.login

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.login.CheckConnect
import kr.ac.tukorea.whereareu.databinding.FragmentPatientOtpBinding
import kr.ac.tukorea.whereareu.presentation.nok.MainNokActivity
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.dementia.MainDementiaActivity
import kr.ac.tukorea.whereareu.util.LoginUtil.repeatOnStarted

class PatientOtpFragment : BaseFragment<FragmentPatientOtpBinding>(R.layout.fragment_patient_otp) {
    private val viewModel: LoginViewModel by activityViewModels()

    override fun initObserver() {
        binding.viewModel = viewModel

        lifecycleScope.launch {
            viewModel.nokIdentityFlow.collect{
                if (it.name != "사용자"){
                    val spf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
                    spf.edit{
                        putString("name", it.name)
                        putString("phone", it.phoneNumber)
                    }
                }
            }
        }
        repeatOnStarted {
            viewModel.eventFlow.collect {
                handleEvent(it)
            }
        }
    }

    override fun initView() {
        binding.view = this
    }
    fun onClickBackBtn() {
        findNavController().popBackStack()
    }

    fun onClickInputDone() {
        //viewModel.checkConnected(CheckConnect(binding.displayOtpTv.text.toString()))
        val intent = Intent(requireContext(), MainDementiaActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
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
                val intent = Intent(requireContext(), MainDementiaActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}