package kr.ac.tukorea.whereareu.presentation.login.dementia

import android.content.Context.MODE_PRIVATE
import android.view.Gravity
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.edit
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest
import kr.ac.tukorea.whereareu.databinding.FragmentPatientOtpBinding
import kr.ac.tukorea.whereareu.databinding.ToastLayoutBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.login.LoginViewModel
import kr.ac.tukorea.whereareu.util.extension.repeatOnStarted
import kr.ac.tukorea.whereareu.util.extension.showToastOnView

@AndroidEntryPoint
class PatientOtpFragment : BaseFragment<FragmentPatientOtpBinding>(R.layout.fragment_patient_otp) {
    private val viewModel: LoginViewModel by activityViewModels()
    private val navigator by lazy {
        findNavController()
    }
    // 뒤로가기 버튼 callback 커스텀
    private val callback = object : OnBackPressedCallback(true) {
       override fun handleOnBackPressed() {
           viewModel.onBackPressedAtDementiaOtp(true)
           navigator.popBackStack()
       }
   }

    override fun initObserver() {
        binding.viewModel = viewModel

        repeatOnStarted {
            viewModel.dementiaKeyFlow.collect {
                binding.displayOtpTv.text = it
            }
        }

        repeatOnStarted {
            viewModel.navigateToDementiaMainEvent.collect {
                //보호자 정보 저장
                val spf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
                spf.edit {
                    putString("name", it.nokName)
                    putString("phone", it.nokPhoneNumber)
                    putString("key", it.nokKey)
                    commit()
                }


                if (navigator.currentDestination?.id == R.id.patientOtpFragment) {
                    navigator.navigate(R.id.action_patientOtpFragment_to_dementiaAuthorityPageFragment)
                }
            }
        }

        repeatOnStarted {
            viewModel.toastEvent.collect{
                requireActivity().showToastOnView(requireContext(), it, binding.finishBtn.bottom)
            }
        }
    }

    override fun initView() {
        binding.view = this
        onClickBackBtn()

        // 뒤로가기 버튼 callback 등록
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun onClickBackBtn() {
        binding.backBtn.setOnClickListener {
            viewModel.onBackPressedAtDementiaOtp(true)
            navigator.popBackStack(R.id.patientIdentifyFragment, false)
        }
    }

    fun onClickInputDone() {
        viewModel.checkConnected(CheckInterConnectRequest(binding.displayOtpTv.text.toString()))
    }
}