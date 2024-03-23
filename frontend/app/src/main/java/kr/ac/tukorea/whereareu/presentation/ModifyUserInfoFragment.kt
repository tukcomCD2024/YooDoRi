package kr.ac.tukorea.whereareu.presentation

import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentModifyUserInfoBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.nok.setting.SettingViewModel
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.hideKeyboard
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.setOnEditorActionListener
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.showKeyboard

class ModifyUserInfoFragment : BaseFragment<FragmentModifyUserInfoBinding>(R.layout.fragment_modify_user_info) {
    private val viewModel : SettingViewModel by activityViewModels()
    private val navigator by lazy {
        findNavController()
    }


    override fun initView() {
        binding.view = this
        binding.userPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.otherUserPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        with(binding) {
            userNameEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if (validName()) {
                    userNameEt.error = null
                } else {
                    userNameEt.error = "최소 2자의 한글을 입력해주세요"
                }
            }
            phoneNumberEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if (validPhone()) {
                    phoneNumberTextInputLayout.error = null
                    phoneNumberEt.hideKeyboard()
                } else {
                    phoneNumberTextInputLayout.error = "전화번호 형식이 다릅니다.\n입력 예시) 010-1234-5678"
                }
            }
        }
    }

    fun onClickBackBtn() {
        navigator.popBackStack()
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