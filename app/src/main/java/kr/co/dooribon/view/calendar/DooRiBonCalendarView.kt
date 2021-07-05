package kr.co.dooribon.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.dooribon.databinding.ViewCalendar
import kr.co.dooribon.view.calendar.adapter.CalendarMonthAdapter
import kr.co.dooribon.view.calendar.entity.CalendarMonthEntity
import java.time.LocalDate
import java.time.YearMonth

/**
 * Created by SSong-develop 2021.07.04
 *
 * TODO : 함수화 구성을 다시 한번 생각해야할거 같음
 * TODO : 주말의 경우 textView가 주황색으로 나와야 한다.
 * TODO : Date를 클릭했을 경우 이 값을 가져올 수 있게 해야한다.
 *
 * thisMonth
 * nextMonth
 * afterNextMonth
 */
class DooRiBonCalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: ViewCalendar =
        ViewCalendar.inflate(LayoutInflater.from(context), this, true)

    // 7월 8월 9월을 보여줄 adapter
    private val calendarAdapter = CalendarMonthAdapter()

    private var thisMonth = LocalDate.now()
    private var nextMonth = thisMonth.plusMonths(1)
    private var afterNextMonth = thisMonth.plusMonths(2)

    init {
        setMonthView()
    }

    private fun setMonthView() {
        val dayInMonth = daysInMonthList(thisMonth)
        val nextDayInMonth = daysInMonthList(nextMonth)
        val nextNextDayInMonth = daysInMonthList(afterNextMonth)

        val calendarEntityList =
            mutableListOf(dayInMonth, nextDayInMonth, nextNextDayInMonth)

        binding.rvCalendar.apply {
            adapter = calendarAdapter
            layoutManager = LinearLayoutManager(context)
        }
        calendarAdapter.submitList(calendarEntityList)
    }

    private fun daysInMonthList(date: LocalDate): CalendarMonthEntity {
        val daysInMonthList = mutableListOf<String>()
        // 년도와 날짜
        val yearMonth = YearMonth.from(date)

        // 한 달에 몇일이 있는지
        val daysInMonth = yearMonth.lengthOfMonth()

        // ex. 7월이면 7월 1일 ,8월이면 8월1일 이런식
        val firstOfMonth = date.withDayOfMonth(1)
        // ex. 7월 한주에 몇일이 있는지 , 2021년 7월은 목요일부터 시작임
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        // daysInMonthList
        // 여기서 주말을 감지하는 로직을 짜고 , 예를 들어 나누기를 통해서 주말이면 true ,아니면 false로
        // boolean값을 줘서 감지를 하면 될거 같습니다.
        // TODO : 여기 로직이 좀 이상하다.
        // TODO : 7 9월 기준으로 뒤에 빈칸이 많아 지는데 이를 해결해줄 뭔가의 방법을 생각해야한다.
        for (i in 1..42) {
            if (dayOfWeek == 7) {
                if ((i - dayOfWeek) > 0 && i <= daysInMonth + dayOfWeek) {
                    daysInMonthList.add((i - dayOfWeek).toString())
                }
            } else {
                // 여기 조건문은 다시 한번 생각을 해봐야할 거 같습니다.
                if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                    daysInMonthList.add("")
                } else {
                    daysInMonthList.add((i - dayOfWeek).toString())
                }
            }
        }

        return CalendarMonthEntity(yearMonth.year, yearMonth.month.value, daysInMonthList)
    }

    fun previous() {
        thisMonth = thisMonth.minusMonths(1)
        setMonthView()
    }

    fun next() {
        thisMonth = thisMonth.plusMonths(1)
        nextMonth = thisMonth.plusMonths(1)
        afterNextMonth = thisMonth.plusMonths(2)
        setMonthView()
    }

}