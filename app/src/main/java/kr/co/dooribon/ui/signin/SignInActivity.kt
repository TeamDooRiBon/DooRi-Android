package kr.co.dooribon.ui.signin

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kr.co.dooribon.R
import kr.co.dooribon.application.MainApplication.Companion.sharedPreferenceModule
import kr.co.dooribon.application.MainApplication.Companion.viewModelModule
import kr.co.dooribon.databinding.ActivitySignInBinding
import kr.co.dooribon.ui.home.HomeActivity
import kr.co.dooribon.utils.debugE
import kr.co.dooribon.utils.getIntent

class SignInActivity : AppCompatActivity() {

    private val binding: ActivitySignInBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
    }

    private val viewModel by viewModels<AuthViewModel> { viewModelModule.authViewModel }

    private val callback = { token : OAuthToken? , error: Throwable? ->
        if(error != null) {
            debugE("error!")
        } else if (token != null) {
            if(token.accessToken != ""){
                viewModel.loginKakaoAccount(token.accessToken, token.refreshToken)
                debugE(token.accessToken)
                debugE(token.refreshToken)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            lifecycleOwner = this@SignInActivity
            vm = viewModel
            activity = this@SignInActivity
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loading.collect {
                    when (it) {
                        // View 처리 해주면 될 거 같습니다.
                        AuthViewModel.UIState.Start -> {

                        }
                        AuthViewModel.UIState.Progress -> {

                        }
                        AuthViewModel.UIState.Complete -> {
                            startActivity(getIntent<HomeActivity>())
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                            finish()
                        }
                        AuthViewModel.UIState.Error -> {

                        }
                    }
                }
            }
        }

        binding.btnKakao.setOnClickListener { login() }
    }

    private fun login() {
        if (sharedPreferenceModule.isFirstAuthLaunch) {
            // 카카오 계정 로그인
            UserApiClient.instance.loginWithKakaoAccount(this,callback = callback)
        } else {
            // api 호출
            viewModel.login()
        }
    }
}