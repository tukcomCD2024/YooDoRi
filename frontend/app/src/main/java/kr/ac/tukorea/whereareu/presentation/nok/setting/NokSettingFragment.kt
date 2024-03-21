package kr.ac.tukorea.whereareu.presentation.nok.setting

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.DialogSettingUpdateTimeBinding
import kr.ac.tukorea.whereareu.databinding.FragmentNokSettingBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel

class NokSettingFragment: BaseFragment<FragmentNokSettingBinding>(R.layout.fragment_nok_setting) {
    private val viewModel: NokHomeViewModel by activityViewModels()
    override fun initObserver() {
    }
    override fun initView() {
        val spf = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        val otherSpf = requireActivity().getSharedPreferences("OtherUser", Context.MODE_PRIVATE)
        val upTime = requireActivity().getSharedPreferences("UpdateTime", MODE_PRIVATE)
        binding.userNameTv.text = spf.getString("name", "")

        val isDementia = spf.getBoolean("isDementia", true)
        binding.userTypeTv.text = if (isDementia) "보호대상자" else "보호자"
        binding.otherNameTv.text = if (isDementia) "보호자 이름" else "보호대상자 이름"
        binding.otherPhoneTv.text = if (isDementia) "보호자 전화번호" else "보호대상자 전화번호"
        binding.userNameTv.text = spf.getString("name", "")
        binding.userPhoneNumberTv.text = spf.getString("phone", "")

        binding.otherNameEditTv.setText(otherSpf.getString("name", ""))
        binding.otherPhoneNumberTv.setText((otherSpf.getString("phone", "")))

        /*binding.testBtn.setOnClickListener {
            val duration = binding.durationEt.text.toString().toLong()
            viewModel.setUpdateDuration(duration * 10000)
        }*/
        binding.updateEditBtn.setOnClickListener {
            val dialog = SetUpdateTimeDialogFragment{time ->
                viewModel.setUpdateDuration(time.toLong())
                binding.updateTimeTv.text = time
            }
            dialog.show(childFragmentManager, dialog.tag)
        }
    }
}