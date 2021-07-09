package kr.co.dooribon.ui.newtrip

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityDatePickBinding
import kr.co.dooribon.view.calendarpicker.entity.SelectionMode

class DatePickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatePickBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_date_pick)

        binding.fragCalendar.setOnRangeSelectedListener { startDate, endDate, startLabel, endLabel ->
            binding.btEnterButton.text = "$startLabel ~ $endLabel 등록하기"
        }
        binding.fragCalendar.setOnStartSelectedListener { startDate, label ->
            binding.btEnterButton.text = "시작과 끝나는 날짜를 눌러주세요"
        }
        binding.fragCalendar.setMode(SelectionMode.RANGE)
    }

}