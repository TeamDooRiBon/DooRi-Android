package kr.co.dooribon.ui.newtrip

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.ActivityDatePickBinding
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

    private fun configureEnterButton(){
        binding.btEnterButton.setOnClickListener {
            // TODO : 화면 네비게이팅만 해주시면 될거 같습니다
            binding.fragCalendar.getSelectedDate().let {
                if(it.first != null && it.second != null){
                    val intent = Intent(this,AddTravelActivity::class.java)
                    intent.putExtra("startDate",DateUtil.convertStringToDateDot(it.first!!))
                    intent.putExtra("endDate",DateUtil.convertStringToDateDot(it.second!!))
                    startActivity(intent)
                    finish()
                }else{
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