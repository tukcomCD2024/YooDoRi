package kr.ac.tukorea.whereareu.presentation.login

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kr.ac.tukorea.whereareu.R

public class LoginFragmentDirections private constructor() {
  public companion object {
    public fun actionLoginFragmentToNokIdentityFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_nokIdentityFragment)

    public fun actionLoginFragmentToPatientFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_loginFragment_to_patientFragment)
  }
}
