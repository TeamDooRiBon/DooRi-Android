package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.dooribon.databinding.ActivityScheduleAddBinding

class ScheduleAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}