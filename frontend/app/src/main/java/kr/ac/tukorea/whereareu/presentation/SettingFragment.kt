package kr.ac.tukorea.whereareu.presentation

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
//import kotlinx.coroutines.flow.EmptyFlow.collect
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.home.LocationInfo
import kr.ac.tukorea.whereareu.databinding.FragmentSettingBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.util.location.InternalFileStorageUtil
import kr.ac.tukorea.whereareu.util.location.LocationService
import kr.ac.tukorea.whereareu.util.location.LocationService.Companion.ACCELEROMETER_SENSOR
import kr.ac.tukorea.whereareu.util.location.LocationService.Companion.GYRO_SENSOR
import kr.ac.tukorea.whereareu.util.location.LocationService.Companion.MAGNETIC_SENSOR
import kr.ac.tukorea.whereareu.util.sensor.AccelerometerSensor
import kr.ac.tukorea.whereareu.util.sensor.GyroScopeSensor
import kr.ac.tukorea.whereareu.util.sensor.MagneticFieldSensor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    @Inject
    lateinit var accelerometerSensor: AccelerometerSensor

    @Inject
    lateinit var magneticFieldSensor: MagneticFieldSensor

    @Inject
    lateinit var gyroScopeSensor: GyroScopeSensor

    private val fileStorageUtil by lazy {
        InternalFileStorageUtil(requireContext())
    }

    private lateinit var stopJob: Job
    private lateinit var walkJob: Job
    private lateinit var carJob: Job
    private lateinit var subwayJob: Job

    private var sensorValueList =
        mutableListOf<List<Float>>(emptyList(), emptyList(), emptyList(), emptyList())

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

        initSensor()
        collectStopSensorValue()
        collectWalkSensorValue()
        collectCarSensorValue()
        collectSubwaySensorValue()

        stopStopSensorValue()
        stopWalkSensorValue()
        stopCarSensorValue()
        stopSubwaySensorValue()
    }

    private fun initSensor() {
        accelerometerSensor.startListening()
        accelerometerSensor.setOnSensorValuesChangedListener { values ->
            sensorValueList[ACCELEROMETER_SENSOR] = values
        }
        gyroScopeSensor.startListening()
        gyroScopeSensor.setOnSensorValuesChangedListener { values ->
            sensorValueList[GYRO_SENSOR] = values
        }
        magneticFieldSensor.startListening()
        magneticFieldSensor.setOnSensorValuesChangedListener { values ->
            sensorValueList[MAGNETIC_SENSOR] = values
        }
    }

    private fun writeToFile(): String{
        val currentTime = getCurrentTime()
        return "${sensorValueList[ACCELEROMETER_SENSOR][0]}, " +
                "${sensorValueList[ACCELEROMETER_SENSOR][1]}, " +
                "${sensorValueList[ACCELEROMETER_SENSOR][2]}, " +
                "${sensorValueList[GYRO_SENSOR][0]}, " +
                "${sensorValueList[GYRO_SENSOR][1]}, " +
                "${sensorValueList[GYRO_SENSOR][2]}, " +
                "${sensorValueList[MAGNETIC_SENSOR][0]}, " +
                "${sensorValueList[MAGNETIC_SENSOR][1]}, " +
                "${sensorValueList[MAGNETIC_SENSOR][2]}, ${currentTime[0]} ${currentTime[1].trim()}"
    }

    private fun collectStopSensorValue() {
        binding.collectStopBtn.setOnClickListener {
            stopJob = lifecycleScope.launch {
                while (true) {
                    fileStorageUtil.createMovementStatusFile(
                        "stop",
                        writeToFile()
                    )
                    delay(1000)
                }
            }
        }
    }

    private fun stopStopSensorValue() {
        binding.stopStopBtn.setOnClickListener {
            lifecycleScope.launch{
                stopJob.cancelAndJoin()
            }
        }
    }

    private fun collectWalkSensorValue() {
        binding.collectWalkBtn.setOnClickListener {
            walkJob = lifecycleScope.launch {
                while (true) {
                    fileStorageUtil.createMovementStatusFile(
                        "walk",
                        writeToFile()
                    )
                    delay(1000)
                }
            }
        }
    }

    private fun stopWalkSensorValue() {
        binding.stopWalkBtn.setOnClickListener {
            lifecycleScope.launch{
                walkJob.cancelAndJoin()
            }
        }
    }

    private fun collectCarSensorValue() {
        binding.collectCarBtn.setOnClickListener {
            carJob = lifecycleScope.launch {
                while (true) {
                    fileStorageUtil.createMovementStatusFile(
                        "car",
                        writeToFile()
                    )
                    delay(1000)
                }
            }
        }
    }

    private fun stopCarSensorValue() {
        binding.stopCarBtn.setOnClickListener {
            lifecycleScope.launch{
                carJob.cancelAndJoin()
            }
        }
    }

    private fun collectSubwaySensorValue() {
        binding.collectSubwayBtn.setOnClickListener {
            subwayJob = lifecycleScope.launch {
                while (true) {
                    fileStorageUtil.createMovementStatusFile(
                        "subway",
                        writeToFile()
                    )
                    delay(1000)
                }
            }
        }
    }

    private fun stopSubwaySensorValue() {
        binding.stopSubwayBtn.setOnClickListener {
            lifecycleScope.launch{
                subwayJob.cancelAndJoin()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentTime(): List<String> {
        Locale.setDefault(Locale.KOREA)
        val currentTime = System.currentTimeMillis()
        val date = Date(currentTime)
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val now = sdf.format(date)
        Log.d("now", now)
        return now.split(" ")
    }

    companion object {
        const val GYRO_SENSOR = 0
        const val ACCELEROMETER_SENSOR = 1
        const val MAGNETIC_SENSOR = 2
    }
}