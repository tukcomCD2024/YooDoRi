package kr.ac.tukorea.whereareu.presentation.login.nok

import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentNokIdentityBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.login.LoginViewModel
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.hideKeyboard
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.setOnEditorActionListener
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.showKeyboard

class NokIdentityFragment :
    BaseFragment<FragmentNokIdentityBinding>(R.layout.fragment_nok_identity) {
        private val viewModel: LoginViewModel by activityViewModels()

    override fun initObserver() {
        binding.viewModel = viewModel
    }

    override fun initView() {
        binding.view = this
        binding.phoneNumberEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        with(binding) {
            nameEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT){
                if(validName()){
                    nameTextInputLayout.error = null
                    phoneNumberEt.showKeyboard()
                }
                else{
                    nameTextInputLayout.error = "최소 2자의 한글을 입력해주세요"
                }
            }
            phoneNumberEt.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
                if(validPhone()){
                    phoneNumberTextInputLayout.error = null
                    phoneNumberEt.hideKeyboard()
                } else {
                    phoneNumberTextInputLayout.error = "전화번호 형식이 다릅니다.\n예시) 010-1234-5678"
                }
            }
        }
    }

    fun onClickBackBtn() {
        findNavController().popBackStack()
    }

    fun onClickInputDone() {
        binding.nameTextInputLayout.error = if(!validName()) "최소 2자의 한글을 입력해주세요" else null

        if (!validPhone()){
            binding.phoneNumberTextInputLayout.error = "전화번호 형식이 다릅니다.\n예시) 010-1234-5678"
            return
        }

        val name = binding.nameEt.text.toString().trim()
        val phoneNumber = binding.phoneNumberEt.text.toString().trim()

        val action = NokIdentityFragmentDirections.actionNokIdentityFragmentToNokOtpFragment(
            name, phoneNumber)
        findNavController().navigate(action)
    }

    private fun validName() = !binding.nameEt.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.nameEt.text!!)


    private fun validPhone() = !binding.phoneNumberEt.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.phoneNumberEt.text!!)

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\n?"
        private const val REGEX_PHONE = "^01([016789])-([0-9]{3,4})-([0-9]{4})"
    }
}