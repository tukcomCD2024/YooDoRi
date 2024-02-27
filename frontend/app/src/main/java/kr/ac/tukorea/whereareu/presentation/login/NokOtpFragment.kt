package kr.ac.tukorea.whereareu.presentation.login

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.inputmethod.EditorInfo
import androidx.core.content.edit
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest
import kr.ac.tukorea.whereareu.databinding.FragmentNokOtpBinding
import kr.ac.tukorea.whereareu.presentation.MainActivity
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.util.EditTextUtil.hideKeyboard
import kr.ac.tukorea.whereareu.util.EditTextUtil.setOnEditorActionListener

class NokOtpFragment : BaseFragment<FragmentNokOtpBinding>(R.layout.fragment_nok_otp) {
    private val viewModel: LoginViewModel by activityViewModels()
    private val args: NokOtpFragmentArgs by navArgs()

    override fun initObserver() {
        binding.viewModel = viewModel

        lifecycleScope.launch{
            viewModel.dementiaIdentityFlow.collect{
                if (it.name != "사용자") {

                    val spf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
                    spf.edit{
                        putString("name", it.name)
                        putString("phone", it.phoneNumber)
                    }
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
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
                } else {
                    otpTextInputLayout.error = "6자리의 인증번호를 입력해주세요."
                }
            }
        }
    }

    fun onClickBackBtn() {
        findNavController().popBackStack()
    }

    fun onClickInputDone() {
        if (!validOtp()) {
            binding.otpTextInputLayout.error = "6자리의 인증번호를 입력해주세요."
            return
        }
        val spf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
        val key = binding.otpEt.text.toString()
        spf.edit {
            putString("key", key)
            putString("name", args.name)
            putString("phone", args.phone)
            putBoolean("isNok", true)
            apply()
        }

        viewModel.sendNokIdentity(NokIdentityRequest(key, args.name, args.phone))
    }

    private fun validOtp() = !binding.otpEt.text.isNullOrBlank()
            && REGEX_OTP.toRegex().matches(binding.otpEt.text!!)

//    private fun handleEvent(event: LoginViewModel.Event) {
//        when (event) {
//            LoginViewModel.Event.Fail -> {
//                binding.otpTextInputLayout.error = "올바른 인증번호를 입력해주세요."
//            }
//
//            else -> {
//                val intent = Intent(requireContext(), MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//            }
//        }
//    }

    companion object {
        private const val REGEX_OTP = "^([0-9]{6})"
    }
}