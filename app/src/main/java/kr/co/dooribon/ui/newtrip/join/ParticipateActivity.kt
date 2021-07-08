package kr.co.dooribon.ui.newtrip.join

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityParticipateBinding
import kr.co.dooribon.ui.home.HomeActivity

class ParticipateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParticipateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.participate_fragment_container_view, ParticipateJoinFragment())
            .commitNow()

        backBtnClickListener()
    }

    private fun backBtnClickListener() {
        binding.ivParticiBack.setOnClickListener {
            finish()
        }
    }
}