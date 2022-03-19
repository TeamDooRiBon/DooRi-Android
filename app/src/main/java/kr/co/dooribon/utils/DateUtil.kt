package kr.co.dooribon.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private val simpleDateFormatDotWithOutTime by lazy {
        SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    }

    private val simpleDateFormatBarWithOutTime by lazy {
        SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
    }

    private val simpleDateFormatDotWithTime by lazy {
        SimpleDateFormat("yyyy-MM-dd`T`HH:mm:ss", Locale.KOREA)
    }

    private val simpleDateFormatBarWithTime by lazy {
        SimpleDateFormat("yyyy-MM-dd`T`HH:mm:ss", Locale.KOREA)
    }

    fun convertStringToDateBar(stringDate: String?): String? {
        return if (stringDate != "_ _") {
            simpleDateFormatBarWithOutTime.format(stringDate)
        } else {
            null
        }
    }

    fun convertStringToStringWithOutTime(stringDate: String): String {
        return convertDateToStringBar(simpleDateFormatBarWithOutTime.parse(stringDate))
    }

    fun convertStringToDate(stringDate: String): Date =
        simpleDateFormatBarWithOutTime.parse(stringDate)

    fun convertDateToStringDot(date: Date): String {
        return simpleDateFormatDotWithOutTime.format(date)
    }

    // Date를 bar형태의 String으로 변경
    fun convertDateToStringBar(date: Date): String {
        return simpleDateFormatBarWithOutTime.format(date)
    }

    fun countDday(date: Date): Long {
        return try {
            // Date를 받아서 이를 변환해주도록 하는게 함수를 호출할 때 깔끔해질거 같음
            val todayCalendar = Calendar.getInstance()
            val dDayCalendar = Calendar.getInstance()
            dDayCalendar.time = date
            val oneDayTime = 24 * 60 * 60 * 1000

            val todayTime = todayCalendar.timeInMillis
            val dDayTime = dDayCalendar.timeInMillis

            ((dDayTime - todayTime) / oneDayTime) + 1
        } catch (e: Exception) {
            debugE(e.toString())
            -1
        }
    }

    /**
     * 서버에서 데이터를 받거나 보내줄 때, 달, 날짜를 표현하기 위해
     * 앞에 0을 붙이는 경우가 많다. 그 때 addzero를 통해 활용하면 편한다.
     * */
    fun String.addZero() =
        if (this.toInt() < 10) {
            "0".plus(this)
        } else {
            this
        }

}