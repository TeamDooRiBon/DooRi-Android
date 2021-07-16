package kr.co.dooribon.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityOnboardingBinding
import kr.co.dooribon.ui.newtrip.join.ParticipateJoinFragment
import kr.co.dooribon.ui.signin.SignInActivity
import kr.co.dooribon.utils.getIntent

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.onboarding_fragment_container_view, OnboardingFirstFragment())
            .commitNow()
    }

}