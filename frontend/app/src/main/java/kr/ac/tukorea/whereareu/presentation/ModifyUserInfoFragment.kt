package kr.ac.tukorea.whereareu.presentation

import android.content.Context.MODE_PRIVATE
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.setting.ModifyUserInfoRequest
import kr.ac.tukorea.whereareu.databinding.FragmentModifyUserInfoBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.nok.setting.SettingViewModel
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.hideKeyboard
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.setOnEditorActionListener
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.showKeyboard

@AndroidEntryPoint
class ModifyUserInfoFragment :
    BaseFragment<FragmentModifyUserInfoBinding>(R.layout.fragment_modify_user_info) {
    private val viewModel: SettingViewModel by activityViewModels()
    private val navigator by lazy {
        findNavController()
    }

    override fun initObserver() {

    }


    override fun initView() {
        binding.view = this
        binding.userPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        binding.otherUserPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        with(binding) {
            userNameEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                val userName = userNameEt.text.toString().trim()
                if (userName.isBlank()) {
                    userNameEt.error = null
                } else {
                    if (validUserName()) {
                        userNameEt.error = null
                        userNameEt.hideKeyboard()
                    } else {
                        userNameEt.error = "최소 2자의 한글을 입력해주세요"
                    }
                }
            }
            userPhoneEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                val userPhone = userPhoneEt.text.toString().trim()
                if (userPhone.isBlank()) {
                    userPhoneEt.error = null
                } else {
                    if (validUserPhone()) {
                        userPhoneEt.error = null
                        userPhoneEt.hideKeyboard()
                    } else {
                        userPhoneEt.error = "전화번호 형식이 다릅니다.\n입력 예시) 010-1234-5678"
                    }
                }
            }
            otherUserNameEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                val otherUserName = otherUserNameEt.text.toString().trim()
                if (otherUserName.isBlank()) {
                    otherUserNameEt.error = null
                } else {
                    if (validOtherUserName()) {
                        otherUserNameEt.error = null
                        otherUserNameEt.hideKeyboard()
                    } else {
                        otherUserNameEt.error = "최소 2자의 한글을 입력해주세요"
                    }
                }
            }
            otherUserPhoneEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
                val otherUserPhone = otherUserPhoneEt.text.toString().trim()
                if (otherUserPhone.isBlank()){
                    otherUserPhoneEt.error = null
                } else {
                    if (validOtherUserPhone()) {
                        otherUserPhoneEt.error = null
                        otherUserPhoneEt.hideKeyboard()
                    } else {
                        otherUserPhoneEt.error = "전화번호 형식이 다릅니다.\n입력 예시) 010-1234-5678"
                    }
                }
            }

            binding.finishBtn.setOnClickListener {
                if(userNameEt.text.isBlank() && userPhoneEt.text.isBlank() && otherUserNameEt.text.isBlank() && otherUserPhoneEt.text.isBlank()){
                    showMessage("변경 사항이 없습니다")
                    return@setOnClickListener
                }
                binding.userNameEt.error = if (!validUserName()) "최소 2자의 한글을 입력해주세요" else null
                binding.otherUserNameEt.error =
                    if (!validOtherUserName()) "최소 2자의 한글을 입력해주세요" else null
                binding.userPhoneEt.error =
                    if (!validUserPhone()) "전화번호 형식이 다릅니다.\n예시) 010-1234-5678" else null
                binding.otherUserPhoneEt.error =
                    if (!validOtherUserPhone()) "전화번호 형식이 다릅니다.\n예시) 010-1234-5678" else null

                val spf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
                val key = spf.getString("key", "")

                viewModel.setUpdateUserName(ModifyUserInfoRequest(0,0, key, binding.userNameEt.text.toString().trim(),""))
            }
        }
    }
//    fun onCLickFinishBtn() {
//        val spf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
//        viewModel.setUpdateUserName(
//            ModifyUserInfoRequest(0, 0, spf.getString("key", ""), binding.userNameEt.text.toString().trim(), "")
//        )
//    }
    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
    fun onClickBackBtn() {
//        navigator.popBackStack()
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun validUserName() = binding.userNameEt.text.isBlank()
            || REGEX_NAME.toRegex().matches(binding.userNameEt.text!!)
    private fun validUserPhone() = binding.userPhoneEt.text.isBlank()
            || REGEX_PHONE.toRegex().matches(binding.userPhoneEt.text!!)
    private fun validOtherUserName() = binding.otherUserNameEt.text.isBlank()
            || REGEX_NAME.toRegex().matches(binding.otherUserNameEt.text!!)
    private fun validOtherUserPhone() = binding.otherUserPhoneEt.text.isBlank()
            || REGEX_PHONE.toRegex().matches(binding.otherUserPhoneEt.text!!)

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\n?"
        private const val REGEX_PHONE = "^01([016789])-([0-9]{3,4})-([0-9]{4})"
    }

}