package kr.ac.tukorea.whereareu.presentation.login.nok

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kr.ac.tukorea.whereareu.R

public class NokOtpFragmentDirections private constructor() {
  public companion object {
    public fun actionNokOtpFragmentToNokAuthorityPageFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_nokOtpFragment_to_nokAuthorityPageFragment)
  }
}
