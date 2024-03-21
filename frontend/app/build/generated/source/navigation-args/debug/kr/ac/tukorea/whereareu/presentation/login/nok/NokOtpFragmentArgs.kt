package kr.ac.tukorea.whereareu.presentation.login.nok

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class NokOtpFragmentArgs(
  public val name: String,
  public val phone: String
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("name", this.name)
    result.putString("phone", this.phone)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("name", this.name)
    result.set("phone", this.phone)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): NokOtpFragmentArgs {
      bundle.setClassLoader(NokOtpFragmentArgs::class.java.classLoader)
      val __name : String?
      if (bundle.containsKey("name")) {
        __name = bundle.getString("name")
        if (__name == null) {
          throw IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue")
      }
      val __phone : String?
      if (bundle.containsKey("phone")) {
        __phone = bundle.getString("phone")
        if (__phone == null) {
          throw IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"phone\" is missing and does not have an android:defaultValue")
      }
      return NokOtpFragmentArgs(__name, __phone)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): NokOtpFragmentArgs {
      val __name : String?
      if (savedStateHandle.contains("name")) {
        __name = savedStateHandle["name"]
        if (__name == null) {
          throw IllegalArgumentException("Argument \"name\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"name\" is missing and does not have an android:defaultValue")
      }
      val __phone : String?
      if (savedStateHandle.contains("phone")) {
        __phone = savedStateHandle["phone"]
        if (__phone == null) {
          throw IllegalArgumentException("Argument \"phone\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"phone\" is missing and does not have an android:defaultValue")
      }
      return NokOtpFragmentArgs(__name, __phone)
    }
  }
}
