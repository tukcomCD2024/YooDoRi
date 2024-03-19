package kr.ac.tukorea.whereareu.presentation

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ModifyUserInfoFragment : Fragment() {


    fun onClickBackBtn() {
        findNavController().popBackStack()
    }

}