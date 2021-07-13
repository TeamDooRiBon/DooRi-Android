package kr.co.dooribon.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    private val simpleDateFormatDot = SimpleDateFormat("yyyy.MM.dd", Locale.KOREA)
    private val simpleDateFormatBar = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)

    fun convertStringToDateBar(stringDate: String?): String? {
        return if (stringDate != "_ _") {
            simpleDateFormatBar.format(stringDate)
        } else {
            null
        }
    }

    fun convertStringToDate(stringDate: String): Date = simpleDateFormatBar.parse(stringDate)

    // TODO : convertDateToStringDot이라고 해야지 멍청이 훈기야
    fun convertStringToDateDot(date: Date): String {
        return simpleDateFormatDot.format(date)
    }

    fun countDday(date : Date): Long {
        try{
            // Date를 받아서 이를 변환해주도록 하는게 함수를 호출할 때 깔끔해질거 같음
            val todayCalendar = Calendar.getInstance()
            val dDayCalendar = Calendar.getInstance()
            dDayCalendar.time = date

            val todayTime = todayCalendar.timeInMillis
            val dDayTime = dDayCalendar.timeInMillis

            return dDayTime - todayTime
        } catch (e : Exception){
            debugE(e.toString())
            return -1
        }
    }

}