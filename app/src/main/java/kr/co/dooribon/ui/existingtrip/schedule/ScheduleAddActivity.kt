package kr.co.dooribon.ui.existingtrip.schedule

import android.app.TimePickerDialog
import android.media.tv.TvContract
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityScheduleAddBinding
import kr.co.dooribon.dialog.ScheduleTimeBottomSheetDialog
import java.util.*

class ScheduleAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScheduleAddBinding
    private var isClickable = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timePickerClickListener()
        notAddClickListener()
        scheduleAddBtnClickListener()
        addBackBtnClickListener()
    }
    val timechange = ScheduleTimeBottomSheetDialog()
    fun timeChange(ampm : String, hour : String, minute : String){
        if(binding.tvTimepickerAmpm1.text != ampm){
            binding.tvTimepickerAmpm1.text = ampm
        }
    }

    private fun timePickerClickListener(){
        binding.clTimepicker1.setOnClickListener{
            ScheduleTimeBottomSheetDialog().show(supportFragmentManager,"timepicker")
            //timeChange(timechange.binding.npAmPm.toString(), timechange.binding.npHour.toString(), timechange.binding.npMinute.toString())
        }
        binding.clTimepicker2.setOnClickListener {
            ScheduleTimeBottomSheetDialog().show(supportFragmentManager, "timepicker")
        }
    }
    private fun notAddClickListener() {
        binding.ivScheduleNotadd.setOnClickListener {
            if (isClickable) {
                binding.ivScheduleNotadd.setImageResource(R.drawable.ic_btn_notadd_active)
                binding.etScheduleAddLocation.apply {
                    setText(null)
                    isClickable = false
                    isEnabled = false
                    setBackgroundResource(R.drawable.bg_edit_gray)
                    hint = ""
                }
                isClickable = false
            } else if (!isClickable) {
                binding.ivScheduleNotadd.setImageResource(R.drawable.ic_btn_notadd)
                binding.etScheduleAddLocation.apply {
                    isEnabled = true
                    setBackgroundResource(R.drawable.bg_edit_text_gray)
                }
                isClickable = true
            }

        }
    }

    private fun scheduleAddBtnClickListener() {
        binding.btnScheduleAdd.setOnClickListener {
            finish()
        }
    }

    private fun addBackBtnClickListener() {
        binding.ivScheduleAddBack.setOnClickListener {
            finish()
        }
    }
}