package kr.co.dooribon.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentOnboardingFirstBinding
import kr.co.dooribon.ui.newtrip.join.ParticipateCheckFragment
import kr.co.dooribon.ui.signin.SignInActivity
import kr.co.dooribon.utils.getIntent

class OnboardingFirstFragment : Fragment() {
    private lateinit var binding : FragmentOnboardingFirstBinding

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
    }
    fun nextBtnClick(){
        binding.btnLoadingNext.setOnClickListener {
            val onboardingsecondFragment = OnboardingSecondFragment()

            /*startActivity(getIntent<SignInActivity>())
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()*/
        }
    }

}