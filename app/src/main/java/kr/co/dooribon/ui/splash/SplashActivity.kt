package kr.co.dooribon.ui.splash

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kr.co.dooribon.R
import kr.co.dooribon.application.MainApplication.Companion.sharedPreferenceModule
import kr.co.dooribon.databinding.ActivitySplashBinding
import kr.co.dooribon.ui.signin.SignInActivity
import kr.co.dooribon.utils.getIntent

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            delay(2000)
            if (sharedPreferenceModule.isFirstLaunch) {
                startActivity(getIntent<OnboardingActivity>())
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            } else {
                startActivity(getIntent<SignInActivity>())
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                finish()
            }
        }
    }
}