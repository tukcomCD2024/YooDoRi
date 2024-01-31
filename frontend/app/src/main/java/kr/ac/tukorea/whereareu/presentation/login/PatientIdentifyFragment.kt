package kr.ac.tukorea.whereareu.presentation.login

import android.content.Context
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentPatientIdentifyBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.login.EditTextUtil.hideKeyboard
import kr.ac.tukorea.whereareu.presentation.login.EditTextUtil.setOnEditorActionListener
import kr.ac.tukorea.whereareu.presentation.login.EditTextUtil.showKeyboard
import okhttp3.Interceptor.Companion.invoke

class PatientIdentifyFragment :
    BaseFragment<FragmentPatientIdentifyBinding>(R.layout.fragment_patient_identify) {
    private lateinit var viewModel: LoginViewModel
    override fun initObserver() {
        viewModel = ViewModelProvider(requireActivity())[LoginViewModel::class.java]
        binding.viewModel = viewModel

        with(viewModel) {
            testError.observe(this@PatientIdentifyFragment) {
                android.util.Log.d("test error", it)
            }
            testSuccess.observe(this@PatientIdentifyFragment){
                if(it == "success"){
                    findNavController().navigate(R.id.action_patientIdentifyFragment_to_patientOtpFragment)
                }
            }
        }
    }

    override fun initView() {
        binding.view = this
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
            phoneNumberEt.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT){
                if(validPhone()){
                    phoneNumberTextInputLayout.error = null
                    phoneNumberEt.hideKeyboard()
                } else {
                    phoneNumberTextInputLayout.error = "전화번호 형식이 다릅니다.\n입력 예시) 01012345678"
                }
            }
        }
    }

    fun onClickBackBtn(){
        findNavController().popBackStack()
    }

    fun onClickInputDone(){
        binding.nameTextInputLayout.error = if(!validName())"최소 2자의 한글을 입력해주세요" else null


        if (!validPhone()){
            binding.phoneNumberTextInputLayout.error = "전화번호 형식이 다릅니다.\n예시) 01012345678"
            return
        }

        viewModel.sendDementiaIdentity(binding.nameEt.text.toString(), binding.phoneNumberEt.text.toString())
        //findNavController().navigate(R.id.action_patientIdentifyFragment_to_patientOtpFragment)
    }

    private fun validName() = !binding.nameEt.text.isNullOrBlank()
            && REGEX_NAME.toRegex().matches(binding.nameEt.text!!)

    private fun validPhone() = !binding.phoneNumberEt.text.isNullOrBlank()
            && REGEX_PHONE.toRegex().matches(binding.phoneNumberEt.text!!)

    companion object {
        private const val REGEX_NAME = "^[가-힣]{2,}\$"
        private const val REGEX_PHONE = "^01([016789])([0-9]{4})([0-9]{4})"
    }
}