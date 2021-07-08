package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityScheduleAddBinding

class ScheduleAddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScheduleAddBinding
    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        notAddClickListener()
        scheduleAddBtnClickListener()
        addBackBtnClickListener()
    }
    private fun notAddClickListener(){
        binding.ivScheduleNotadd.setOnClickListener{
            if (isClickable) {
                binding.ivScheduleNotadd.setImageResource(R.drawable.ic_btn_notadd_active)
                binding.etScheduleAddLocation.setClick
                isClickable = false
            }
            else if (!isClickable){
                binding.ivScheduleNotadd.setImageResource(R.drawable.ic_btn_notadd)
                isClickable = true
            }

        }
    }
    private fun scheduleAddBtnClickListener(){
        binding.btnScheduleAdd.setOnClickListener{
            finish()
        }
    }
    private fun addBackBtnClickListener() {
        binding.ivScheduleAddBack.setOnClickListener {
            finish()
        }
    }
}