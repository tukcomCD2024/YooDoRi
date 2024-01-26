import androidx.navigation.fragment.findNavController
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.databinding.FragmentPatientIdentifyBinding
import kr.ac.tukorea.whereareu.databinding.FragmentNokOtpBinding
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment

class PatientIdentifyFragment: BaseFragment<FragmentPatientIdentifyBinding>(R.layout.fragment_patient_otp) {

    override fun initObserver() {

    }

    override fun initView() {
        binding.view = this
    }

    fun onClickBackBtn(){
        findNavController().popBackStack()
    }

    fun onClickInputDone(){
        findNavController().navigate(R.id.action_nokIdentityFragment_to_nokOtpFragment)
    }
}