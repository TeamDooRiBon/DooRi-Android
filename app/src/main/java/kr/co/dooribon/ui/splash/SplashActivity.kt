package kr.co.dooribon.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kr.co.dooribon.R
import kr.co.dooribon.application.MainApplication.Companion.sharedPreferenceModule
import kr.co.dooribon.databinding.ActivitySplashBinding
import kr.co.dooribon.ui.signin.SignInActivity
import kr.co.dooribon.utils.getIntent
import kr.co.dooribon.utils.shortToast

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            delay(2000)
            if (sharedPreferenceModule.isFirstLaunch) {
                shortToast("온보딩이 시작되면 됩니다.")
            } else {
                shortToast("온보딩이 시작되지 않으면 됩니다.")
            }
            startActivity(getIntent<SignInActivity>())
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
            finish()
        }
    }
}