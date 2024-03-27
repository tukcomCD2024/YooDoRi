package kr.ac.tukorea.whereareu.presentation

import android.content.Context.MODE_PRIVATE
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.edit
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
import kr.ac.tukorea.whereareu.util.extension.repeatOnStarted

@AndroidEntryPoint
class ModifyUserInfoFragment :
    BaseFragment<FragmentModifyUserInfoBinding>(R.layout.fragment_modify_user_info) {
    private val viewModel: SettingViewModel by activityViewModels()
    private val navigator by lazy {
        findNavController()
    }

    override fun initObserver() {
        binding.viewModel = viewModel
//        repeatOnStarted {
//            viewModel.updateUserInfo.collect{userInfo ->
//                userInfo?.let {
//                    // 수정한 정보 저장
//                    val nokSpf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
//                    nokSpf.edit {
//                        putString("name", binding.userNameEt.text.toString())
//                        putString("phone", binding.userPhoneEt.text.toString())
//                        commit()
//                    }
//                }
//            }
//        }
        viewModel?.let { // viewModel이 null이 아닌 경우에만 실행
            repeatOnStarted {
                it.updateUserInfo?.collect { userInfo ->
                    userInfo?.let { // userInfo가 null이 아닌 경우에만 실행
                        // 수정한 정보 저장
                        val nokSpf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
                        nokSpf.edit {
                            putString("name", binding.userNameEt.text.toString())
                            putString("phone", binding.userPhoneEt.text.toString())
                            commit()
                        }
                    }
                }
            }
        } ?: run {
            // viewModel이 null인 경우 처리할 로직 작성
            // 예를 들어, Toast 메시지를 표시하거나 로그를 출력할 수 있습니다.
            Toast.makeText(requireContext(), "ViewModel이 null입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun initView() {
        binding.view = this
        binding.userPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())

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
            binding.finishBtn.setOnClickListener {
                binding.userNameEt.error = if (!validUserName()) "최소 2자의 한글을 입력해주세요" else null
                binding.userPhoneEt.error =
                    if (!validUserPhone()) "전화번호 형식이 다릅니다.\n예시) 010-1234-5678" else null

                val spf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
                val key = spf.getString("key", "")

                viewModel.setUpdateUserInfo(ModifyUserInfoRequest(0, key, binding.userNameEt.text.toString().trim(), binding.userPhoneEt.text.toString().trim()))
            }
        }
    }
    fun onClickBackBtn() {
//        navigator.popBackStack()
        requireActivity().supportFragmentManager.popBackStack()
    }

    private fun validUserName() = binding.userNameEt.text.isNullOrBlank()
            || REGEX_NAME.toRegex().matches(binding.userNameEt.text!!)

    private fun validUserPhone() = binding.userPhoneEt.text.isNullOrBlank()
            || REGEX_PHONE.toRegex().matches(binding.userPhoneEt.text!!)

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\n?"
        private const val REGEX_PHONE = "^01([016789])-([0-9]{3,4})-([0-9]{4})"
    }

}