package kr.ac.tukorea.whereareu.presentation

import android.content.Context.MODE_PRIVATE
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.EmptyFlow.collect
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentSettingBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override fun initObserver() {

    }


    override fun initView() {
        val spf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
        val otherSpf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
        binding.userNameTv.text = spf.getString("name", "")

        val isDementia = spf.getBoolean("isDementia", true)
        binding.userTypeTv.text = if (isDementia) "보호대상자" else "보호자"
        binding.otherNameTv.text = if (isDementia) "보호자 이름" else "보호대상자 이름"
        binding.otherPhoneTv.text = if (isDementia) "보호자 전화번호" else "보호대상자 전화번호"
        binding.userNameTv.text = spf.getString("name", "")
        binding.userPhoneNumberTv.text = spf.getString("phone", "")

        binding.otherNameEditTv.setText(otherSpf.getString("name", ""))
        binding.otherPhoneNumberTv.setText((otherSpf.getString("phone", "")))

    }
}