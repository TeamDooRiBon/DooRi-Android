package kr.co.dooribon.ui.existingtrip.schedule

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import kr.co.dooribon.R
import kr.co.dooribon.databinding.FragmentScheduleBinding
import kr.co.dooribon.ui.existingtrip.schedule.adapters.DateScheduleAdapter
import kr.co.dooribon.ui.existingtrip.schedule.adapters.TravelDate
import java.text.DateFormat
import java.time.LocalDate
import java.util.*

class ScheduleFragment : Fragment() {

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var datesList: List<TravelDate>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        setDataAdapter()
        return binding.root
    }

    private fun setDataAdapter() {
        val dateAdapter = DateScheduleAdapter()
        val dateRV = binding.rvDays
        dateRV.adapter = dateAdapter
        datesList = listOf(
            TravelDate("D1", 2021, 7, 29),
            TravelDate("D2", 2021, 7, 30),
            TravelDate("D3", 2021, 7, 31),
            TravelDate("D4", 2021, 8, 1),
            TravelDate("D5", 2021, 8, 2),
            TravelDate("D6", 2021, 8, 3),
            TravelDate("D7", 2021, 8, 4),
            TravelDate("D8", 2021, 8, 5),
            TravelDate("D9", 2021, 8, 6),
            TravelDate("D10", 2022, 9, 7),
        )
        dateAdapter.setItemList( // dummy data
            datesList
        )

        dateAdapter.setItemClickListener(object : DateScheduleAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int) {
                Log.e("position", position.toString())
                setDate(datesList[position].year, datesList[position].month)
                setBelowDate(datesList[position])
            }
        })
    }

    /**
     * 다른 달 날짜를 클릭하면 달을 표시하는 text를 바꿔줍니다.
     * 연도도 다른 연도가 들어오면 바꿔줍니다.
     * */
    private fun setDate(year: Int, month: Int) {
        if (binding.tvYear.text != year.toString()) {
            binding.tvYear.text = year.toString()
        }
        if (binding.tvMonth.text != month.toString()) {
            binding.tvMonth.text = month.toString()
        }
    }

    /**
     * Horizontal Recyclerview 아래에 며칠 째 여행인지 표시해주는 textview와 오늘 며칠인지 보여주는 textview 설정하는 부분
     * TODO 서버
     */
    private fun setBelowDate(dateInfo: TravelDate) {
        val dday = dateInfo.dday
        val ddayStr = "DAY ".plus(dday.substring(1, dday.length))
        val monthStr = if (dateInfo.month >= 10) { // 달 계산할 때 10보다 작으면 앞에 0붙이는 것
            dateInfo.month.toString()
        } else {
            "0".plus(dateInfo.month.toString())
        }
        val dateStr = if (dateInfo.date >= 10) { // 일 계산할 때 10보다 작으면 앞에 0붙이는 것
            dateInfo.date.toString()
        } else {
            "0".plus(dateInfo.date.toString())
        }
        val dateFormatStr = "".plus(dateInfo.year.toString()).plus("-").plus(monthStr).plus("-")
            .plus(dateStr) // 2021-07-21 과 같은 스트링
        val dayOfWeek = LocalDate.parse(dateFormatStr).dayOfWeek // 라이브러리 통해 요일 가져오는 코드
        val dayOfWeekKr = when (dayOfWeek.toString()) {
            "MONDAY" -> {
                "월요일"
            }
            "TUESDAY" -> {
                "화요일"
            }
            "WEDNESDAY" -> {
                "수요일"
            }
            "THURSDAY" -> {
                "목요일"
            }
            "FRIDAY" -> {
                "금요일"
            }
            "SATURDAY" -> {
                "토요일"
            }
            "SUNDAY" -> {
                "일요일"
            }
            else -> {
                Log.e("ScheduleFragment", "DateParsingError")
            }
        }
        val fullDateStr =
            dateInfo.year.toString().plus(". ").plus(monthStr).plus(". ")
                .plus(dateStr).plus(" ").plus(dayOfWeekKr) // "2021.07.29 목요일" 과같은 스트링

        binding.tvDday.text = ddayStr
        binding.tvDate.text = fullDateStr
    }

}