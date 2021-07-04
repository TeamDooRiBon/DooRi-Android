package kr.co.dooribon.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import kr.co.dooribon.databinding.ViewCalendar
import kr.co.dooribon.view.calendar.adapter.CalendarMonthAdapter
import kr.co.dooribon.view.calendar.entity.CalendarMonthEntity
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

/**
 * Created by SSong-develop 2021.07.04
 *
 * 현재 날짜가 뜨게 하는건 됐고 이제 다다음 달까지 나오게 하는 로직을 구상하면 될 거 같습니다.
 *
 * TODO : S6의 경우 화면이 작기 때문에 조금 짤려서 17일까지만 나오게 됨;;;
 * TODO : 함수화 구성을 다시 한번 생각해야할거 같음
 * TODO : 주말의 경우 textView가 주황색으로 나와야 한다.
 * TODO : Date를 클릭했을 경우 이 값을 가져올 수 있게 해야한다.
 * TODO : 8월의 경우 1일이 일요일부터 시작하는데 이게 한칸을 전부 띄워서 나오게 된다. 이거 수정해야할 거같음
 */
class DooRiBonCalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding: ViewCalendar =
        ViewCalendar.inflate(LayoutInflater.from(context), this, true)

    private val calendarAdapter = CalendarMonthAdapter()
    private var selectedDate = LocalDate.now()
    private var selectedNextDate = selectedDate.plusMonths(1)
    private var selectedNextNextDate = selectedNextDate.plusMonths(1)

    init {
        setMonthView()
    }

    private fun setMonthView() {
        val dayInMonth = daysInMonthList(selectedDate)
        val nextDayInMonth = daysInMonthList(selectedNextDate)
        val nextNextDayInMonth = daysInMonthList(selectedNextNextDate)

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
        val yearMonth = YearMonth.from(date)

        val daysInMonth = yearMonth.lengthOfMonth()

        val firstOfMonth = date.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        // daysInMonthList
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthList.add("")
            } else {
                daysInMonthList.add((i - dayOfWeek).toString())
            }
        }

        return CalendarMonthEntity(yearMonth.year, yearMonth.month.value, daysInMonthList)
    }

    private fun monthYearFromDate(date: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    fun previous() {
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    fun next() {
        selectedDate = selectedDate.plusMonths(1)
        selectedNextDate = selectedNextDate.plusMonths(1)
        selectedNextNextDate = selectedNextNextDate.plusMonths(1)
        setMonthView()
    }

}