package kr.ac.tukorea.whereareu.util.extension

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.Gravity
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.ToastLayoutBinding

fun Activity.showToastOnView(context: Context, text: String, y: Int) {
    val binding = ToastLayoutBinding.inflate(layoutInflater)
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getRealSize(size)
    val screenY = size.y
    binding.run {
        tv.text = text
        val toast = Toast(context)
        toast.view = binding.root

        binding.root.setBackgroundResource(R.drawable.toast_bg)
        toast.setGravity(Gravity.BOTTOM, 0, screenY - y)
        toast.show()
    }
}

fun Activity.showToastShort(context: Context, text: String){
    //Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    val binding = ToastLayoutBinding.inflate(layoutInflater)
    binding.run {
        tv.text = text
        val toast = Toast(context)
        toast.view = binding.root
        toast.duration = Toast.LENGTH_SHORT
        binding.root.setBackgroundResource(R.drawable.toast_bg)
        //toast.setGravity(Gravity.BOTTOM, 0, screenY - y)
        toast.show()
    }
}

fun Activity.showToastLong(context: Context, text: String){
    //Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    val binding = ToastLayoutBinding.inflate(layoutInflater)
    binding.run {
        tv.text = text
        val toast = Toast(context)
        toast.view = binding.root
        toast.duration = Toast.LENGTH_LONG
        binding.root.setBackgroundResource(R.drawable.toast_bg)
        //toast.setGravity(Gravity.BOTTOM, 0, screenY - y)
        toast.show()
    }
}