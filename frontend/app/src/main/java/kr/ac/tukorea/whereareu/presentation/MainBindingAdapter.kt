package kr.ac.tukorea.whereareu.presentation

import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.naver.maps.map.widget.LogoView
import kr.ac.tukorea.whereareu.R

import kr.ac.tukorea.whereareu.presentation.nok.NokMainActivity.Companion.HOME_TAB
import kr.ac.tukorea.whereareu.presentation.nok.NokMainActivity.Companion.SETTING_TAB
import kr.ac.tukorea.whereareu.presentation.nok.NokMainViewModel
import kr.ac.tukorea.whereareu.presentation.nok.safearea.SafeAreaViewModel
import kr.ac.tukorea.whereareu.util.extension.EditTextUtil.setOnEditorActionListener

object MainBindingAdapter {
    @BindingAdapter("app:ringtoneText")
    @JvmStatic
    fun setRingtoneText(view: TextView, ringtone: Int) {
        val ringtoneText = when (ringtone) {
            0 -> "무음"
            1 -> "진동"
            2 -> "벨소리"
            else -> "알 수 없음"
        }
        view.text = ringtoneText
    }

    @BindingAdapter("app:ringtoneImage")
    @JvmStatic
    fun setRingtoneImage(view: ImageView, ringtone: Int) {
        val drawableId = when (ringtone) {
            0 -> R.drawable.ic_bell_off_24
            1 -> R.drawable.ic_vibrate_24
            2 -> R.drawable.ic_bell_24
            else -> 0
        }
        view.setImageDrawable(ContextCompat.getDrawable(view.context, drawableId))
    }

    @BindingAdapter("app:movementStatus")
    @JvmStatic
    fun setMovementStatus(view: TextView, status: Int) {
        val movementStatus = when (status) {
            1 -> "정지"
            2 -> "도보"
            3 -> "차량"
            4 -> "지하철"
            else -> "알수없음"
        }
        view.text = movementStatus
    }

    @BindingAdapter("app:navigateEvent")
    @JvmStatic
    fun setBottomSheetIconVisible(view: ImageView, navigateEvent: Int) {
        val color = when (navigateEvent) {
            R.id.nokHomeFragment -> R.color.gray40
            R.id.locationHistoryFragment -> R.color.white
            R.id.meaningfulPlaceFragment -> R.color.gray40
            R.id.nokSettingFragment -> R.color.white
            R.id.safeAreaFragment -> R.color.white
            else -> R.color.gray40
        }
        view.setColorFilter(ContextCompat.getColor(view.context, color))
    }

    @BindingAdapter("app:navigateEvent", "app:isPredicted")
    @JvmStatic
    fun Group.setHomeComponentBtnVisible(navigateEvent: Int, isPredicted: Boolean) {
        Log.d("binding adapter isPredicted", isPredicted.toString())
        if (navigateEvent in HOME_TAB) {
            this.isVisible = if (isPredicted) {
                false
            } else {
                true
            }
        } else {
            this.isVisible = false
        }
    }

    @BindingAdapter("app:searchAddress")
    @JvmStatic
    fun EditText.searchAddress(viewModel: SafeAreaViewModel) {
        this.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
            viewModel.fetchCoord(this.text.toString())
        }
    }

    @BindingAdapter("app:setLogoVisible")
    @JvmStatic
    fun LogoView.setVisible(navigateEvent: Int) {
        when (navigateEvent) {
            R.id.safeAreaFragment,
            R.id.meaningfulPlaceDetailFragment,
            in SETTING_TAB -> this.visibility = View.GONE
        }
    }

    @BindingAdapter(
        "app:navigateEvent",
        "app:isPredicted",
        "app:currentNavigateMenuEvent",
        "app:isNavigationEventDuplicate"
    )
    @JvmStatic
    fun ConstraintLayout.setBehaviorState(
        navigateEvent: Int,
        isPredicted: Boolean,
        currentNavigateMenuEvent: NokMainViewModel.NavigateMenuEvent,
        isNavigationEventDuplicate: Boolean
    ) {
        // bottom sheet visibility 제어
        this.isVisible = if (navigateEvent in HOME_TAB) {
            isPredicted
        } else {
            true
        }

        // bottom sheet state 제어
        val bottomSheetBehavior = BottomSheetBehavior.from(this)
        bottomSheetBehavior.isDraggable = when (navigateEvent) {
            in SETTING_TAB, R.id.safeAreaFragment -> false
            else -> true
        }

        when (currentNavigateMenuEvent) {
            is NokMainViewModel.NavigateMenuEvent.Setting -> {
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }

            is NokMainViewModel.NavigateMenuEvent.SafeArea -> {
                bottomSheetBehavior.state =
                    if (currentNavigateMenuEvent.destination == R.id.safeAreaFragment) {
                        BottomSheetBehavior.STATE_EXPANDED
                    } else {
                        BottomSheetBehavior.STATE_HALF_EXPANDED
                    }
            }

            else -> {
                if (!isNavigationEventDuplicate) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
                }
            }
        }
    }
}