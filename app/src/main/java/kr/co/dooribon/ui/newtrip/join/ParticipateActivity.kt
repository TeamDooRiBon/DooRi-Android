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

        BackBtnClickListener()
    }

    // 일단 뒤로가기 버튼을 누르면 HomeActivity로 넘어가도록 했습니다!
    private fun BackBtnClickListener() {
        binding.ivParticiBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}