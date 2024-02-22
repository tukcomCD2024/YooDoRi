package kr.ac.tukorea.whereareu.presentation

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.EmptyFlow.collect
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentSettingBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.login.LoginViewModel
import kr.ac.tukorea.whereareu.util.LoginUtil.repeatOnStarted
import kr.ac.tukorea.whereareu.util.handleApi

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    private val viewModel: LoginViewModel by activityViewModels()
    override fun initObserver() {
        repeatOnStarted {
            viewModel.eventFlow.collect {
            }
        }
    }


    override fun initView() {
        val spf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
        val dementiaInfoSpf = requireActivity().getSharedPreferences("DementiaInfoSP", Context.MODE_PRIVATE)
        binding.userNameTv.text = spf.getString("name", "")

        val isDementia = spf.getBoolean("isDementia", true)
        binding.userTypeTv.text = if (isDementia) "보호대상자" else "보호자"
        binding.otherNameTv.text = if (isDementia) "보호자 이름" else "보호대상자 이름"
        binding.otherPhoneTv.text = if (isDementia) "보호자 전화번호" else "보호대상자 전화번호"
        binding.userNameTv.text = spf.getString("name", "")
        binding.userPhoneNumberTv.text = spf.getString("phone", "")

        if (isDementia) {

        } else {
            binding.otherNameEditTv.setText(dementiaInfoSpf.getString("name", ""))
            binding.otherPhoneNumberTv.setText((dementiaInfoSpf.getString("phone", "")))

        }

//        if (isDementia) {
//            binding.otherNameEditTv.text = "보호대상자"
//
//        } else {
////            binding.otherNameEditTv.text = viewModel.dementiaNameFlow.value
//            lifecycleScope.launch {
//                viewModel.dementaNameFlow.collect {
//                    binding.otherNameEditTv.text = it?.dementiaInfo?.dementiaName
//                }
//            }
////            lifecycleScope.launch {
////                viewModel.dementiaPhoneFlow.collect {
////                    binding.otherPhoneNumberTv.text = it
////                }
////            }
//
//            val dementiaName = viewModel.getDementiaName()
//            binding.otherNameEditTv.text = dementiaName
//
//            val dementiaPhone = viewModel.getDementiaPhone()
//            binding.otherPhoneNumberTv.text = dementiaPhone
//
////            lifecycleScope.launch{
////                repeatOnLifecycle(Lifecycle.State.CREATED) {
////                    launch{
////                        viewModel.dementiaNameFlow.collect{
////                            binding.otherNameEditTv.text = it
////                        }
////                        viewModel.dementiaPhoneFlow.collect{
////                            binding.otherPhoneNumberTv.text = it
////                        }
////                    }
////                }
////            }
//
//        }

    }
}