package kr.ac.tukorea.whereareu.presentation.login.dementia

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kr.ac.tukorea.whereareu.R

public class PatientOtpFragmentDirections private constructor() {
  public companion object {
    public fun actionPatientOtpFragmentToDementiaAuthorityPageFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_patientOtpFragment_to_dementiaAuthorityPageFragment)
  }
}
