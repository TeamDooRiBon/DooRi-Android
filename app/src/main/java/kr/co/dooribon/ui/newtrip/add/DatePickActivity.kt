package kr.co.dooribon.ui.newtrip.add

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityDatePickBinding
import kr.co.dooribon.domain.entity.PickDatePair
import kr.co.dooribon.utils.DateUtil
import kr.co.dooribon.utils.shortToast
import kr.co.dooribon.view.calendarpicker.entity.SelectionMode

class DatePickActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDatePickBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_date_pick)

        configureCalendar()
        configureBackButton()
        configureEnterButton()
    }

    private fun configureEnterButton() {
        binding.btEnterButton.setOnClickListener {
            binding.fragCalendar.getSelectedDate().let {
                if (it.first != null && it.second != null) {
                    // setResult로 데이터를 반환하고 종료합니다.
                    setResult(
                        RESULT_OK,
                        Intent().apply {
                            putExtra(
                                "datePair",
                                PickDatePair(
                                    DateUtil.convertStringToDateDot(it.first!!),
                                    DateUtil.convertStringToDateDot(it.second!!)
                                )
                            )
                        }
                    )
                    finish()
                } else {
                    shortToast("선택된 날짜가 없습니다.")
                }
            }
        }
    }

    private fun configureBackButton() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    // BindingAdapter로 빼버릴 수도 있습니다.
    @SuppressLint("SetTextI18n")
    private fun configureCalendar() {
        binding.fragCalendar.apply {
            setOnRangeSelectedListener { startDate, endDate, startLabel, endLabel ->
                binding.btEnterButton.apply {
                    text = "$startLabel ~ $endLabel 등록하기"
                    setBackgroundColor(
                        ContextCompat.getColor(
                            this@DatePickActivity,
                            R.color.white_pure_9
                        )
                    )
                }
            }
            setOnStartSelectedListener { startDate, label ->
                binding.btEnterButton.apply {
                    text = "시작과 끝나는 날짜를 눌러주세요"
                    /*setBackgroundColor(
                        ContextCompat.getColor(
                            this@DatePickActivity,
                            R.color.gray_gray_7_line
                        )
                    )*/
                }
            }
            setMode(SelectionMode.RANGE)
        }
    }

}