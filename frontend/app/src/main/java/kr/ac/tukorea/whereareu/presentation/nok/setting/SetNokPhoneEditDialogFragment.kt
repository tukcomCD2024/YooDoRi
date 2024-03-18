package kr.ac.tukorea.whereareu.presentation.nok.setting

import android.app.AlertDialog
import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.util.Log
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.DialogSettingUpdateTimeBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseDialogFragment

class SetNokPhoneEditDialogFragment(val setNokPhoneEdit: (Phone) -> Unit): BaseDialogFragment<DialogSettingUpdateTimeBinding>(R.layout.dialog_set_nok_phone_edit) {
    override fun initObserver() {

    }

    override fun initView() {
        val builder = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.dialog_set_nok_phone_edit, null)

        val editPhoneNumber = view.findViewById<>(R.id.phone_number_et)

    }

}