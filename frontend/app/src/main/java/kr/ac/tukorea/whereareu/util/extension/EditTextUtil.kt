package kr.ac.tukorea.whereareu.util.extension

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object EditTextUtil {
    fun EditText.setOnEditorActionListener(action: Int, invoke: () -> Unit){
        setOnEditorActionListener { _, actionId, _ ->
            if(actionId == action){
                invoke()
                true
            } else {
                false
            }
        }
    }

    fun EditText.showKeyboard(){
        this.requestFocus()
        val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    fun EditText.showKeyboardDelay(){
        postDelayed({showKeyboard()}, 200) // 람다 함수를 사용해 함수를 Runnalbe 객체로 변환 가능
    }

    fun EditText.hideKeyboard(){
        this.clearFocus()
        val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
    }
}