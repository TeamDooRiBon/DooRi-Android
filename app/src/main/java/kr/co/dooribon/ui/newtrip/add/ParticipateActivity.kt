package kr.co.dooribon.ui.newtrip.add

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityParticipateBinding

class ParticipateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityParticipateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParticipateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.partici_fragment_container_view, ParticiPutFragment()).commitNow()
    }
}