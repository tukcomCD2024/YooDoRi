package kr.ac.tukorea.whereareu.presentation.nok.setting

import android.content.Context
import android.util.Log
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.DialogSettingUpdateTimeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseDialogFragment

class SetNokPhoneEditDialogFragment(val setUpdateTime: (String) -> Unit): BaseDialogFragment<DialogSettingUpdateTimeBinding>(R.layout.dialog_setting_update_time) {
    //private lateinit var setUpdateTimeListener: SetUpdateTimeListener
    override fun initObserver() {

    }

    override fun initView() {
        val upTime = requireActivity().getSharedPreferences("UpdateTime", Context.MODE_PRIVATE)
        with(binding) {
            numberPicker.minValue = 0
            numberPicker.maxValue = 8

            numberPicker.displayedValues =
                arrayOf("1", "3", "5", "10", "15", "20", "30", "45", "60")
        }


        binding.finishBtn.setOnClickListener {
            with(binding) {
                val selectedValueIndex = numberPicker.value
                val selectedValue = numberPicker.displayedValues[selectedValueIndex]
                upTime.edit().putInt("selectedValueIndex", selectedValueIndex).apply()
                Log.d("SettingFragment", "UpdateTIme: $selectedValue")
                upTime.edit().putString("selectedTime", selectedValue)
                setUpdateTime(selectedValue)
                //setUpdateTimeListener.setUpdateTime(selectedValue)
            }
            dismiss()
        }
    }
    interface SetUpdateTimeListener{
        fun setUpdateTime(time: String)
    }
}