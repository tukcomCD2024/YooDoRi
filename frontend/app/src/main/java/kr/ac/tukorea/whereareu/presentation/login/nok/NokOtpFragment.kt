package kr.ac.tukorea.whereareu.presentation.login.nok

import android.content.Context.MODE_PRIVATE
import android.view.inputmethod.EditorInfo
import androidx.core.content.edit
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.login.request.NokIdentityRequest
import kr.ac.tukorea.whereareu.databinding.FragmentNokOtpBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.login.LoginViewModel
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.hideKeyboard
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.setOnEditorActionListener
import kr.ac.tukorea.whereareu.util.extension.repeatOnStarted
import kr.ac.tukorea.whereareu.util.extension.showToastOnView

@AndroidEntryPoint
class NokOtpFragment : BaseFragment<FragmentNokOtpBinding>(R.layout.fragment_nok_otp) {
    private val viewModel: LoginViewModel by activityViewModels()
    private val args: NokOtpFragmentArgs by navArgs()
    private val navigator by lazy {
        findNavController()
    }

    override fun initObserver() {
        binding.viewModel = viewModel
        repeatOnStarted{
            viewModel.navigateToNokMainEvent.collect {
                // 보호자 정보 저장
                val nokSpf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
                nokSpf.edit {
                    putBoolean("isDementia", false)
                    putString("key", it.nokKey)
                    putString("name", args.name)
                    putString("phone", args.phone)
                    commit()
                }

                // 보호대상자 정보 저장
                val dementiaInfo = it.dementiaInfoRecord
                val dementiaSpf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
                dementiaSpf.edit {
                    putString("name", dementiaInfo.dementiaName)
                    putString("phone", dementiaInfo.dementiaPhoneNumber)
                    putString("key", dementiaInfo.dementiaKey)
                    commit()
                }
                if (navigator.currentDestination?.id == R.id.nokOtpFragment) {
                    navigator.navigate(R.id.action_nokOtpFragment_to_nokAuthorityPageFragment)
                }
            }
        }

        // 에러 메시지 Toast로 출력
        repeatOnStarted {
            viewModel.toastEvent.collect{
                requireActivity().showToastOnView(requireContext(), it, binding.finishBtn.bottom)
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
        navigator.popBackStack()
    }

    fun onClickInputDone() {
        if (!validOtp()) {
            binding.otpTextInputLayout.error = "6자리의 인증번호를 입력해주세요."
            return
        }

        val key = binding.otpEt.text.toString()
        viewModel.sendNokIdentity(NokIdentityRequest(key, args.name, args.phone))
    }

    private fun validOtp() = !binding.otpEt.text.isNullOrBlank()
            && REGEX_OTP.toRegex().matches(binding.otpEt.text!!)

    companion object {
        private const val REGEX_OTP = "^([0-9]{6})"
    }
}