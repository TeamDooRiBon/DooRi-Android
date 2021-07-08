package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.dooribon.databinding.ActivityScheduleEditBinding

class ScheduleEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}