package kr.co.dooribon.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentOnboardingFirstBinding
import kr.co.dooribon.ui.signin.SignInActivity

class OnboardingFirstFragment : Fragment() {
    private lateinit var binding: FragmentOnboardingFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottie.playAnimation()
        nextBtnClick()
        skipBtnClick()
    }

    fun nextBtnClick() {
        binding.btnLoadingNext.setOnClickListener {
            val onboardingsecondFragment = OnboardingSecondFragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.onboarding_fragment_container_view, onboardingsecondFragment)
                .commitNow()
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