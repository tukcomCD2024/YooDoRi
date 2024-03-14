package kr.ac.tukorea.whereareu.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.hilt.android.AndroidEntryPoint
//import kotlinx.coroutines.flow.EmptyFlow.collect
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.databinding.FragmentSettingBinding
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
    }

    private fun collectStopSensorValue(){}
    private fun collectWalkSensorValue(){}
    private fun collectCarSensorValue(){}
    private fun collectStopSensorValue(){}
}