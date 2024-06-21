package kr.ac.tukorea.whereareu.presentation

import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.presentation.nok.home.NokHomeViewModel
import kr.ac.tukorea.whereareu.presentation.nok.safearea.SafeAreaViewModel
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.setOnEditorActionListener
import kr.ac.tukorea.whereareu.util.extension.setRingtoneImage

object BindingAdapter {
    @BindingAdapter("bind:stopStatusPeriod")
    @JvmStatic
    fun setStopStatusPeriod(view: TextView, stopStatusPeriod: String){
        view.text = stopStatusPeriod.replace(",", " ~ ")
    }


    @BindingAdapter("bind:dayOfWeek")
    @JvmStatic
    fun setDayOfWeekColor(view: TextView, dayOfWeek: String){
        val drawable = when(dayOfWeek){
            "토" -> R.drawable.oval_blue
            "일" -> R.drawable.oval_red
            else -> R.drawable.oval_black
        }
        view.background = ContextCompat.getDrawable(view.context, drawable)
    }

    @BindingAdapter("bind:searchAddress")
    @JvmStatic
    fun EditText.searchAddress(viewModel: SafeAreaViewModel){
        this.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE){
            viewModel.fetchCoord(this.text.toString())
        }
    }

}