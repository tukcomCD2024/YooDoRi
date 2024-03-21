package kr.ac.tukorea.whereareu.presentation.login.dementia

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kr.ac.tukorea.whereareu.R

public class PatientIdentifyFragmentDirections private constructor() {
  public companion object {
    public fun actionPatientIdentifyFragmentToPatientOtpFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_patientIdentifyFragment_to_patientOtpFragment)
  }
}
