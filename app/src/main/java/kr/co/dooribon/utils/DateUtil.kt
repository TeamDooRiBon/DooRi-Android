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

    fun convertStringToDateDot(date : Date) : String {
        return simpleDateFormatDot.format(date)
    }
}