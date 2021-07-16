package kr.co.dooribon.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentOnboardingSecondBinding

class OnboardingSecondFragment : Fragment(){
    private lateinit var binding : FragmentOnboardingSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottie.playAnimation()
        nextBtnClick()
    }
    fun nextBtnClick() {
        binding.btnLoadingNext.setOnClickListener {
            val onboardingthirdFragment = OnboardingThirdFragment()

            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.onboarding_fragment_container_view, onboardingthirdFragment)
                .commitNow()
        }
    }
}