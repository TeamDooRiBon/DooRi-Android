package kr.co.dooribon.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.databinding.FragmentOnboardingThirdBinding
import kr.co.dooribon.ui.signin.SignInActivity

class OnboardingThirdFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottie.playAnimation()
        startBtnClick()
        skipBtnClick()
    }

    fun startBtnClick() {
        binding.btnLoadingStart.setOnClickListener {
            val intent = Intent(getActivity(), SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // activity back stack 모두 제거
            startActivity(intent)
        }
    }

    fun skipBtnClick() {
        binding.tvSkip.setOnClickListener {
            val intent = Intent(getActivity(), SignInActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK) // activity back stack 모두 제거
            startActivity(intent)
        }
    }
}