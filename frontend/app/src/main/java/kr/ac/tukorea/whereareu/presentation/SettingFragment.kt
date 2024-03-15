package kr.ac.tukorea.whereareu.presentation

import android.app.AlertDialog
import android.app.ProgressDialog.show
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.NumberPicker
import android.widget.PopupWindow
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.EmptyFlow.collect
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.databinding.FragmentSettingBinding
import kr.ac.tukorea.whereareu.databinding.FragmentSettingUpdateTimeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.util.location.InternalFileStorageUtil
import kr.ac.tukorea.whereareu.util.location.LocationService

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    private val mMessageReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val info = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getSerializableExtra("postInfo", LocationInfo::class.java)
            } else {
                intent?.getSerializableExtra("postInfo") as LocationInfo
            }
            Log.d("info", info.toString())
            binding.postInfoTv.text = "서버에 보낸 정보: " + info.toString()
        }
    }

    override fun initObserver() {
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            mMessageReceiver, IntentFilter("gps")
        )
    }

    override fun initView() {
        val spf = requireActivity().getSharedPreferences("User", MODE_PRIVATE)
        val otherSpf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
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

        binding.startBtn.setOnClickListener {
            Intent(requireActivity().applicationContext, LocationService::class.java).apply {
                action = LocationService.ACTION_START
                requireActivity().startService(this)
            }
        }

        binding.stopBtn.setOnClickListener {
            Intent(requireActivity().applicationContext, LocationService::class.java).apply {
                action = LocationService.ACTION_STOP
                requireActivity().startService(this)
            }
        }

        binding.updateEditBtn.setOnClickListener {
            Log.d("SettingFragment", "onUpdateEditBtnClick")
            val dialogBinding =
                FragmentSettingUpdateTimeBinding.inflate(LayoutInflater.from(requireContext()))
            val numberPicker = dialogBinding.numberPicker

            numberPicker.minValue = 0
            numberPicker.maxValue = 8

            numberPicker.displayedValues =
                arrayOf("1", "3", "5", "10", "15", "20", "30", "45", "60")

            val lastSelectedValueIndex = upTime.getInt("selectedValueIndex", 0)

            // 마지막으로 설정한 값 가져옴
            numberPicker.value = lastSelectedValueIndex


            val builder = AlertDialog.Builder(requireContext())
            builder.setView(dialogBinding.root)

            val dialog = builder.create()
            dialog.create()
            dialogBinding.finishBtn.setOnClickListener {
                dialog.dismiss()

                val selectedValueIndex = numberPicker.value
                val selectedValue = numberPicker.displayedValues[selectedValueIndex]

                upTime.edit().putInt("selectedValueIndex", selectedValueIndex).apply()
                Log.d("SettingFragment", "UpdateTIme: $selectedValue")
                binding.updateTimeTv.text = selectedValue
            }
            dialog.show()
        }
    }
}