package kr.co.dooribon.view.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.dooribon.databinding.ViewCalendar
import kr.co.dooribon.view.calendar.adapter.CalendarMonthAdapter
import kr.co.dooribon.view.calendar.entity.CalendarMonthEntity
import java.time.LocalDate
import java.time.Month
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context,attrs) {

    private val binding : ViewCalendar =
        ViewCalendar.inflate(LayoutInflater.from(context),this,true)

    private val calendarAdapter = CalendarMonthAdapter()
    private var selectedDate = LocalDate.now()

    init {
        setMonthView()
    }

    private fun setMonthView(){
        val dayInMonth = daysInMonthList(selectedDate)
        binding.rvCalendar.apply {
            adapter = calendarAdapter
            layoutManager = LinearLayoutManager(context)
        }
        calendarAdapter.submitList(dayInMonth)
    }

    private fun daysInMonthList(date : LocalDate) : CalendarMonthEntity{
        val daysInMonthList = mutableListOf<String>()
        val yearMonth = YearMonth.from(date)

        val daysInMonth = yearMonth.lengthOfMonth()

        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value

        // daysInMonthList
        for(i in 1..42){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){
                daysInMonthList.add("")
            }
            else{
                daysInMonthList.add((i - dayOfWeek).toString())
            }
        }

        return CalendarMonthEntity(yearMonth.year,yearMonth.month.value,daysInMonthList)
    }

    private fun monthYearFromDate(date : LocalDate) : String {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    fun previous(){
        selectedDate = selectedDate.minusMonths(1)
        setMonthView()
    }

    fun next() {
        selectedDate = selectedDate.plusMonths(1)
        setMonthView()
    }

}