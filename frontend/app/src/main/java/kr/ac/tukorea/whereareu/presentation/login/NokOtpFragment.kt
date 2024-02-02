package kr.ac.tukorea.whereareu.presentation.login

import android.content.Intent
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentNokOtpBinding
import kr.ac.tukorea.whereareu.presentation.MainActivity
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.login.EditTextUtil.hideKeyboard
import kr.ac.tukorea.whereareu.presentation.login.EditTextUtil.setOnEditorActionListener

class NokOtpFragment: BaseFragment<FragmentNokOtpBinding>(R.layout.fragment_nok_otp) {
    private val viewModel: LoginViewModel by activityViewModels()
    private val args: NokOtpFragmentArgs by navArgs()

    override fun initObserver() {
        binding.viewModel = viewModel

        viewModel.apiSuccess.observe(this){
            if(it == "success"){
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
            else{
                binding.otpTextInputLayout.error = "올바른 인증번호를 입력해주세요."
            }
        }
    }

    override fun initView() {
        binding.view = this
        with(binding) {
            otpEt.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                if (validOtp()) {
                    otpTextInputLayout.error = null
                    otpEt.hideKeyboard()
                }
                else{
                    otpTextInputLayout.error = "6자리의 인증번호를 입력해주세요."
                }
            }
        }
    }

    fun onClickBackBtn(){
        findNavController().popBackStack()
    }

    fun onClickInputDone() {
        if(!validOtp()){
            binding.otpTextInputLayout.error = "6자리의 인증번호를 입력해주세요."
            return
        }
        viewModel.sendNokIdentity(binding.otpEt.text.toString(), args.name, args.phone)
    }

    private fun validOtp() = !binding.otpEt.text.isNullOrBlank()
            && REGEX_OTP.toRegex().matches(binding.otpEt.text!!)

    companion object {
        private const val REGEX_OTP = "^([0-9]{6})"
    }
}