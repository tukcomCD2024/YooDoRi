package kr.ac.tukorea.whereareu.presentation.nok.setting

import android.app.AlertDialog
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.telephony.PhoneNumberFormattingTextWatcher
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.core.content.edit
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.DialogSetNokPhoneEditBinding
import kr.ac.tukorea.whereareu.databinding.DialogSettingUpdateTimeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseDialogFragment
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.hideKeyboard
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.setOnEditorActionListener

class SetNokPhoneEditDialogFragment(private val setNokPhoneEdit: (Phone) -> Unit) :
    BaseDialogFragment<DialogSetNokPhoneEditBinding>(R.layout.dialog_set_nok_phone_edit) {
    override fun initObserver() {

    }

    override fun initView() {
        binding.editPhoneEt.addTextChangedListener(PhoneNumberFormattingTextWatcher())
        with(binding) {
            editPhoneEt.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
                if (validPhone()) {
                    textInputLayout.error = null
                    editPhoneEt.hideKeyboard()
                } else {
                    textInputLayout.error = "전화번호 형식이 다릅니다.\n예시) 010-1234-5678"
                }
            }
            binding.finishBtn.setOnClickListener {
                    val spf = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
                    spf.edit {
                        putString("phone", binding.editPhoneEt.text.toString().trim())
                        commit()
                    }
                    Log.d(
                        "SetNokPhoneEditDialog",
                        "UpdatePhone :${binding.editPhoneEt.text.toString()}"
                    )
                dismiss()
                }
                binding.cancleBtn.setOnClickListener {
                    dismiss()
                }
        }
    }

    private fun validPhone() = !binding.editPhoneEt.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.editPhoneEt.text!!)

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\n?"
        private const val REGEX_PHONE = "^01([016789])-([0-9]{3,4})-([0-9]{4})"
    }
}