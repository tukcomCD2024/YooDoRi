package kr.ac.tukorea.whereareu.presentation.login.nok

import android.os.Bundle
import androidx.navigation.NavDirections
import kotlin.Int
import kotlin.String
import kr.ac.tukorea.whereareu.R

public class NokIdentityFragmentDirections private constructor() {
  private data class ActionNokIdentityFragmentToNokOtpFragment(
    public val name: String,
    public val phone: String
  ) : NavDirections {
    public override val actionId: Int = R.id.action_nokIdentityFragment_to_nokOtpFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("name", this.name)
        result.putString("phone", this.phone)
        return result
      }
  }

  public companion object {
    public fun actionNokIdentityFragmentToNokOtpFragment(name: String, phone: String): NavDirections
        = ActionNokIdentityFragmentToNokOtpFragment(name, phone)
  }
}
