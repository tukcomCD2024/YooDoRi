package kr.ac.tukorea.whereareu.presentation

import android.content.Context.MODE_PRIVATE
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.inputmethod.EditorInfo
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoRequest
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

    override fun initObserver(){

    }


    override fun initView() {
        binding.view = this
        binding.userPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.otherUserPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        with(binding) {
            userNameEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if (validUserName()) {
                    userNameEt.error = null
                    userNameEt.hideKeyboard()
                } else {
                    userNameEt.error = "최소 2자의 한글을 입력해주세요"
                }
            }
            userPhoneEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if (validUserPhone()) {
                    userPhoneEt.error = null
                    userPhoneEt.hideKeyboard()
                } else {
                    userPhoneEt.error = "전화번호 형식이 다릅니다.\n입력 예시) 010-1234-5678"
                }
            }
            otherUserNameEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if (validOtherUserName()) {
                    otherUserNameEt.error = null
                    otherUserNameEt.hideKeyboard()
                } else {
                    otherUserNameEt.error = "최소 2자의 한글을 입력해주세요"
                }
            }
            otherUserPhoneEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                if (validOtherUserPhone()) {
                    otherUserPhoneEt.error = null
                    otherUserPhoneEt.hideKeyboard()
                } else {
                    otherUserPhoneEt.error = "전화번호 형식이 다릅니다.\n입력 예시) 010-1234-5678"
                }
            }
            binding.finishBtn.setOnClickListener {
                binding.userNameEt.error = if(!validUserName()) "최소 2자의 한글을 입력해주세요" else null
                binding.otherUserNameEt.error = if(!validOtherUserName()) "최소 2자의 한글을 입력해주세요" else null
//                if (!validUserPhone()){
//                    binding.userPhoneEt.error = "전화번호 형식이 다릅니다.\n예시) 010-1234-5678"
//                    return
//                }
//                if (!validOtherUserPhone()){
//                    binding.otherUserPhoneEt.error = "전화번호 형식이 다릅니다.\n예시) 010-1234-5678"
//                    return
//                }
                binding.userPhoneEt.error = if(!validUserPhone()) "전화번호 형식이 다릅니다.\n예시) 010-1234-5678" else null
                binding.otherUserPhoneEt.error = if(!validOtherUserPhone()) "전화번호 형식이 다릅니다.\n예시) 010-1234-5678" else null

                val userName = binding.userNameEt.text.toString().trim()
                val userPhone = binding.userPhoneEt.text.toString().trim()
                val otherUserName = binding.otherUserNameEt.text.toString().trim()
                val otherUserPhone = binding.otherUserPhoneEt.text.toString().trim()
            }
        }
    }
    fun onCLickFinishBtn(){
        val spf = requireActivity().getSharedPreferences("Usewr", MODE_PRIVATE)
        viewModel.setUpdateUserName(
            ModifyUserInfoRequest(0,0, spf.getString("key", ""), binding.userNameEt.text.toString().trim(), "")
        )
    }
    fun onClickBackBtn() {
        navigator.popBackStack()
    }

    private fun validUserName() = !binding.userNameEt.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.userNameEt.text!!)
    private fun validUserPhone() = !binding.userPhoneEt.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.userPhoneEt.text!!)
    private fun validOtherUserName() = !binding.otherUserNameEt.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.otherUserNameEt.text!!)
    private fun validOtherUserPhone() = !binding.otherUserPhoneEt.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.otherUserPhoneEt.text!!)

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\n?"
        private const val REGEX_PHONE = "^01([016789])-([0-9]{3,4})-([0-9]{4})"
    }

}