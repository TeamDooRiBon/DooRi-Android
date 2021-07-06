package kr.co.dooribon.view.calendarpicker

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.dooribon.R
import kr.co.dooribon.view.calendarpicker.adapter.CalendarAdapter
import kr.co.dooribon.view.calendarpicker.entity.*
import kr.co.dooribon.view.calendarpicker.util.*
import java.util.*
import java.util.Calendar.*

/**
 * 필요없는 기능은 이제 좀 제거하면서 최적화 하면 될거 같음
 *
 * TODO : 주말을 표시해줘야 함
 * TODO : 코드 주석좀 달아놔야할 거 같음
 */
class DooRiBonCalendarPicker : RecyclerView {

    private val timeZone = TimeZone.getDefault()
    private val locale = Locale.KOREA

    private val calendarAdapter = CalendarAdapter()
    private val startCalendar = getInstance(timeZone, locale)
    private val endCalendar = getInstance(timeZone, locale)

    private var calendarData: MutableList<CalendarEntity> = mutableListOf()
    private var startDateSelection: SelectedDate? = null
    private var endDateSelection: SelectedDate? = null
    private var pickerSelectionType = SelectionMode.RANGE

    private var onStartSelectedListener: (startDate: Date, label: String) -> Unit = { _, _ -> }
    private var onRangeSelectedListener: (startDate: Date, endDate: Date, startLabel: String, endLabel: String) -> Unit =
        { _, _, _, _ -> }

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        extractAttributes(attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        extractAttributes(attributeSet)
    }

    init {
        startCalendar.set(HOUR_OF_DAY, 0)
        startCalendar.set(MINUTE, 0)
        startCalendar.set(SECOND, 0)
        startCalendar.set(MILLISECOND, 0)

        endCalendar.time = startCalendar.time
        endCalendar.add(YEAR, 1)

        setBackgroundColor(ContextCompat.getColor(context, R.color.doo_ri_bon_calendar_picker_bg))
        initAdapter()
        initListener()
    }

    // region setter
    fun setRangeDate(startDate: Date, endDate: Date) {
        require(startDate.time <= endDate.time) { "startDate can't be higher than endDate" }

        startCalendar.withTime(startDate)
        endCalendar.withTime(endDate)

        refreshData()
    }

    fun scrollToDate(date: Date) {
        val index =
            calendarData.indexOfFirst { it is CalendarEntity.Day && it.date.isTheSameDay(date) }
        require(index > -1) { "Date to scroll must be included in your Calendar Range Date" }
        smoothScrollToPosition(index)
    }

    fun setSelectionDate(startDate: Date, endDate: Date? = null) {
        itemAnimator = null
        selectDate(startDate)
        if (endDate != null) selectDate(endDate)
    }

    fun setMode(mode: SelectionMode) {
        pickerSelectionType = mode
    }

    fun setOnStartSelectedListener(callback: (startDate: Date, label: String) -> Unit) {
        onStartSelectedListener = callback
    }

    fun setOnRangeSelectedListener(callback: (startDate: Date, endDate: Date, startLabel: String, endLabel: String) -> Unit) {
        onRangeSelectedListener = callback
    }
    // endregion setter

    // region getter
    fun getSelectedDate(): Pair<Date?, Date?> {
        return Pair(startDateSelection?.day?.date, endDateSelection?.day?.date)
    }

    // endregion getter
    private fun initListener() {
        calendarAdapter.onActionListener = { item, position ->
            if (itemAnimator == null) itemAnimator = DefaultItemAnimator()
            if (item is CalendarEntity.Day) onDaySelected(item, position)
        }
    }

    private fun initAdapter() {
        layoutManager = GridLayoutManager(context, TOTAL_COLUMN_COUNT).apply {
            spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return calendarData[position].columnCount
                }
            }
        }
        adapter = calendarAdapter
        refreshData()
    }

    private fun selectDate(date: Date) {
        val index =
            calendarData.indexOfFirst { it is CalendarEntity.Day && it.date.isTheSameDay(date) }
        require(index > -1) {
            "Selection date must be included in your Calendar Range Date"
        }

        onDaySelected(calendarData[index] as CalendarEntity.Day, index)
    }

    private fun refreshData() {
        calendarData = buildCalendarData()
        calendarAdapter.submitList(calendarData)
    }

    // TODO : 여기서 WEEKEND임을 알려줄 로직이 필요하다.
    private fun buildCalendarData(): MutableList<CalendarEntity> {
        val calendarData = mutableListOf<CalendarEntity>()
        val cal = getInstance(timeZone, locale)
        cal.withTime(startCalendar.time)

        val monthDifference = endCalendar.totalMonthDifference(startCalendar)

        cal.set(DAY_OF_MONTH, 1)
        (0..monthDifference).forEach { _ ->
            val totalDayInMonth = cal.getActualMaximum(DAY_OF_MONTH)
            (1..totalDayInMonth).forEach { _ ->
                val day = cal.get(DAY_OF_MONTH)
                val dayOfYear = cal.get(DAY_OF_YEAR)
                val dayOfWeek = cal.get(DAY_OF_WEEK)
                val dateState = if (
                    cal.isBefore(startCalendar)
                    || cal.isAfter(endCalendar)
                ) {
                    DateState.DISABLED
                } else {
                    DateState.WEEKDAY
                }
                when (day) {
                    1 -> {
                        calendarData.add(
                            CalendarEntity.Month(
                                cal.toPrettyMonthString(),
                                dayOfYear.toString()
                            )
                        )
                        calendarData.addAll(createStartEmptyView(dayOfWeek))
                        calendarData.add(
                            CalendarEntity.Day(
                                day.toString(),
                                cal.toPrettyDateString(),
                                cal.time,
                                state = dateState
                            )
                        )
                    }
                    totalDayInMonth -> {
                        calendarData.add(
                            CalendarEntity.Day(
                                day.toString(),
                                cal.toPrettyDateString(),
                                cal.time,
                                state = dateState
                            )
                        )
                        calendarData.addAll(createEndEmptyView(dayOfWeek))
                    }
                    else -> {
                        calendarData.add(
                            CalendarEntity.Day(
                                day.toString(),
                                cal.toPrettyDateString(),
                                cal.time,
                                state = dateState
                            )
                        )
                    }
                }
                cal.add(DATE, 1)
            }
        }
        return calendarData
    }

    // 달력이 끝날 때 또한 비는 요일은 빈칸으로 둬야 한다.
    private fun createEndEmptyView(dayOfWeek: Int): List<CalendarEntity.Empty> {
        val numberOfEmptyView = when (dayOfWeek) {
            SUNDAY -> 6
            MONDAY -> 5
            TUESDAY -> 4
            WEDNESDAY -> 3
            THURSDAY -> 2
            FRIDAY -> 1
            else -> 6
        }

        val listEmpty = mutableListOf<CalendarEntity.Empty>()
        repeat((0 until numberOfEmptyView).count()) { listEmpty.add(CalendarEntity.Empty) }
        return listEmpty
    }

    // 달력이 시작할 때 월요일에서 시작하지 않는 경우, 기존의 요일들을 빈칸으로 둬야한다.
    private fun createStartEmptyView(dayOfWeek: Int): List<CalendarEntity.Empty> {
        val numberOfEmptyView = when (dayOfWeek) {
            MONDAY -> 1
            TUESDAY -> 2
            WEDNESDAY -> 3
            THURSDAY -> 4
            FRIDAY -> 5
            SATURDAY -> 6
            else -> 0
        }

        val listEmpty = mutableListOf<CalendarEntity.Empty>()
        repeat((0 until numberOfEmptyView).count()) { listEmpty.add(CalendarEntity.Empty) }
        return listEmpty
    }

    // 날짜 클릭 시
    private fun onDaySelected(item: CalendarEntity.Day, position: Int) {
        if (item == startDateSelection?.day) return
        when {
            pickerSelectionType == SelectionMode.SINGLE -> {
                if (startDateSelection != null) {
                    calendarData[startDateSelection!!.position] =
                        startDateSelection!!.day.copy(selection = SelectionType.NONE)
                }
                assignAsStartDate(item, position)
            }
            startDateSelection == null -> assignAsStartDate(item,position)
            endDateSelection == null -> {
                if(startDateSelection!!.position > position){
                    calendarData[startDateSelection!!.position] =
                        startDateSelection!!.day.copy(selection = SelectionType.NONE)
                    assignAsStartDate(item,position)
                }else {
                    assignAsStartDate(
                        startDateSelection!!.day,
                        startDateSelection!!.position,
                        true
                    )
                    assignAsEndDate(item,position)
                    highlightDateBetween(startDateSelection!!.position,position)
                }
            }
            else -> {
                resetSelection()
                assignAsStartDate(item,position)
            }
        }
        calendarAdapter.submitList(calendarData)
    }

    // Range를 바꾸는 경우엔 기존에 선택되어 있는 녀석들을 초기화 해주어야 한다.
    private fun resetSelection(){
        val startDatePosition = startDateSelection?.position
        val endDatePosition = endDateSelection?.position

        if(startDatePosition != null && endDatePosition != null) {
            (startDatePosition..endDatePosition).forEach {
                val entity = calendarData[it]
                if(entity is CalendarEntity.Day)
                    calendarData[it] = entity.copy(selection = SelectionType.NONE)
            }
        }
        endDateSelection = null
    }

    // 선택되어 있는 녀석들을 기준으로 끼어있는 녀석들은 전부 선택됐음을 표기해주는 함수
    private fun highlightDateBetween(
        startIndex : Int,
        endIndex : Int
    ) {
        ((startIndex + 1) until endIndex).forEach {
            val entity = calendarData[it]
            if(entity is CalendarEntity.Day){
                calendarData[it] = entity.copy(selection = SelectionType.BETWEEN)
            }
        }
    }

    private fun assignAsStartDate(
        item: CalendarEntity.Day,
        position: Int,
        isRange: Boolean = false
    ) {
        val newItem = item.copy(selection = SelectionType.START, isRange = isRange)
        calendarData[position] = newItem
        startDateSelection = SelectedDate(newItem, position)
        if (!isRange) onStartSelectedListener.invoke(item.date, item.prettyLabel)
    }

    private fun assignAsEndDate(
        item: CalendarEntity.Day,
        position: Int
    ) {
        val newItem = item.copy(selection = SelectionType.END)
        calendarData[position] = newItem
        endDateSelection = SelectedDate(newItem, position)
        onRangeSelectedListener.invoke(
            startDateSelection!!.day.date,
            item.date,
            startDateSelection!!.day.prettyLabel,
            item.prettyLabel
        )
    }

    private fun extractAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DooRiBonCalendarPicker)
        pickerSelectionType =
            SelectionMode.values()[typedArray.getInt(
                R.styleable.DooRiBonCalendarPicker_picker_type,
                0
            )]
        typedArray.recycle()
    }

    data class SelectedDate(val day: CalendarEntity.Day, val position: Int)
}