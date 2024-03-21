package kr.ac.tukorea.whereareu.presentation.nok.home

import androidx.navigation.ActionOnlyNavDirections
import androidx.navigation.NavDirections
import kr.ac.tukorea.whereareu.R

public class NokHomeFragmentDirections private constructor() {
  public companion object {
    public fun actionNokHomeFragmentToPredictLocationFragment(): NavDirections =
        ActionOnlyNavDirections(R.id.action_nokHomeFragment_to_predictLocationFragment)
  }
}
