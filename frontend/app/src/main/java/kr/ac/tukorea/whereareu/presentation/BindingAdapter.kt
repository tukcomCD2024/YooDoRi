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
    @BindingAdapter("bind:ringtoneText")
    @JvmStatic
    fun setRingtoneText(view: TextView, ringtone: Int){
        val ringtoneText = when(ringtone){
            0 -> "무음"
            1 -> "진동"
            2 -> "벨소리"
            else -> "알 수 없음"
        }
        view.text = ringtoneText
    }

    @BindingAdapter("bind:ringtoneImage")
    @JvmStatic
    fun setRingtoneImage(view: ImageView, ringtone: Int){
        val drawableId = when(ringtone){
            0 -> R.drawable.ic_bell_off_24
            1 -> R.drawable.ic_vibrate_24
            2 -> R.drawable.ic_bell_24
            else -> 0
        }
        view.setImageDrawable(ContextCompat.getDrawable(view.context, drawableId))
    }

    @BindingAdapter("bind:movementStatus")
    @JvmStatic
    fun setMovementStatus(view: TextView, status: Int){
        val movementStatus = when(status){
            1 -> "정지"
            2 -> "도보"
            3 -> "차량"
            4 -> "지하철"
            else -> "알수없음"
        }
        view.text=movementStatus
    }

    @BindingAdapter("bind:navigateEvent", "bind:isPredicted")
    @JvmStatic
    fun setBottomSheetVisible(view: ConstraintLayout, navigateEvent: String, isPredicted: Boolean) {
        if(navigateEvent == "Home"){
            view.isVisible = if(isPredicted){
                true
            } else {
                false
            }
        } else {
            view.isVisible = true
        }
    }

    @BindingAdapter("bind:navigateEvent")
    @JvmStatic
    fun setBottomSheetIconVisible(view: ImageView, navigateEvent: String){
        val color = when(navigateEvent){
            "Home" -> R.color.gray40
            "LocationHistory" -> R.color.white
            "MeaningfulPlace" -> R.color.gray40
            "Setting" -> R.color.white
            "SafeArea" -> R.color.white
            "SafeAreaInner" -> R.color.gray40
            else -> R.color.white
        }
        view.setColorFilter(ContextCompat.getColor(view.context, color))
    }

    @BindingAdapter("bind:stopStatusPeriod")
    @JvmStatic
    fun setStopStatusPeriod(view: TextView, stopStatusPeriod: String){
        view.text = stopStatusPeriod.replace(",", " ~ ")
    }

    @BindingAdapter("bind:navigateEvent", "bind:isPredicted")
    @JvmStatic
    fun setHomeComponentBtnVisible(view: TextView, navigateEvent: String, isPredicted: Boolean){
        Log.d("binding adapter djs", isPredicted.toString())
        if(navigateEvent == "Home"){
            view.isVisible = if(isPredicted){
                false
            } else {
                true
            }
        } else {
            view.isVisible = false
        }
    }

    @BindingAdapter("bind:navigateEvent", "bind:isSos")
    @JvmStatic
    fun setHomeComponentBtnVisibleSos(view: TextView, navigateEvent: String, isSos: Boolean) {
        Log.d("binding adapter djs", isSos.toString())
        if(navigateEvent == "Home"){
            view.isVisible = if (isSos) {
                false
            } else {
                true
            }
        } else {
            view.isVisible = false
        }
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