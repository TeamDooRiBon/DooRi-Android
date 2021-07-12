package kr.co.dooribon.ui.signin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivitySignInBinding
import kr.co.dooribon.ui.home.HomeActivity
import kr.co.dooribon.utils.getIntent

class SignInActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_in)

        binding.btnKakao.setOnClickListener {
            startActivity(getIntent<HomeActivity>())
            overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
            finish()
        }
    }
}