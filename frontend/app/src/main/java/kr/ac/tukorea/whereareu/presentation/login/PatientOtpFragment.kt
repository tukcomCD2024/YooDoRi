package kr.ac.tukorea.whereareu.presentation.login

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.edit
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import kr.ac.tukorea.whereareu.R
import kr.ac.tukorea.whereareu.data.model.login.request.CheckInterConnectRequest
import kr.ac.tukorea.whereareu.databinding.FragmentPatientOtpBinding
import kr.ac.tukorea.whereareu.databinding.ToastLayoutBinding
import kr.ac.tukorea.whereareu.presentation.MainActivity
import kr.ac.tukorea.whereareu.presentation.base.BaseFragment
import kr.ac.tukorea.whereareu.util.LoginUtil.repeatOnStarted

class PatientOtpFragment : BaseFragment<FragmentPatientOtpBinding>(R.layout.fragment_patient_otp) {
    private val viewModel: LoginViewModel by activityViewModels()
    private val navigator by lazy {
        findNavController()
    }
    // 뒤로가기 버튼 callback 커스텀
    private val callback = object : OnBackPressedCallback(true) {
       override fun handleOnBackPressed() {
           viewModel.onBackPressedAtDementiaOtp(true)
           navigator.popBackStack()
       }
   }

    override fun initObserver() {
        binding.viewModel = viewModel

        repeatOnStarted {
            viewModel.dementiaKeyFlow.collect {
                binding.displayOtpTv.text = it
            }
        }

        repeatOnStarted {
            viewModel.nokIdentityFlow.collect {
                /*if (it.name != "사용자"){
                    val spf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
                    spf.edit{
                        putString("name", it.name)
                        putString("phone", it.phoneNumber)
                    }
                }*/
                Log.d("event", it.toString())
                val spf = requireActivity().getSharedPreferences("OtherUser", MODE_PRIVATE)
                spf.edit {
                    putString("name", it.nokName)
                    putString("phone", it.nokPhoneNumber)
                    putString("key", it.nokKey)
                    commit()
                }
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        repeatOnStarted {
            viewModel.toastEvent.collect{
                val binding = ToastLayoutBinding.inflate(layoutInflater)
                binding.run{
                    tv.text = it
                    val toast = Toast(requireContext())
                    toast.view = binding.root

                    binding.root.setBackgroundResource(R.drawable.toast_bg)
                    toast.setGravity(Gravity.BOTTOM, 0, 400)
                    toast.show()
                }
            }
        }

        /*repeatOnStarted {
            viewModel.eventFlow.collect {
                handleEvent(it)
            }
        }*/
    }

    override fun initView() {
        binding.view = this
        onClickBackBtn()

        // 뒤로가기 버튼 callback 등록
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun onClickBackBtn() {
        binding.backBtn.setOnClickListener {
            viewModel.onBackPressedAtDementiaOtp(true)
            navigator.popBackStack(R.id.patientIdentifyFragment, false)
        }
    }

    fun onClickInputDone() {
        viewModel.checkConnected(CheckInterConnectRequest(binding.displayOtpTv.text.toString()))
    }

    private fun handleEvent(event: LoginViewModel.Event) {
        when (event) {
            LoginViewModel.Event.Fail -> {
                Toast.makeText(
                    requireContext(),
                    "아직 보호자와 연결이 되지 않았습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> {
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}