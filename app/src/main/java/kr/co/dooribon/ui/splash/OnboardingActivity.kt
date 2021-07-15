package kr.co.dooribon.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityOnboardingBinding
import kr.co.dooribon.ui.signin.SignInActivity
import kr.co.dooribon.utils.getIntent

class OnboardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnboardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lottie.playAnimation()

        nextBtnClick()
    }
    fun nextBtnClick(){
        binding.btnLoadingNext.setOnClickListener {
            startActivity(getIntent<SignInActivity>())
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }
}