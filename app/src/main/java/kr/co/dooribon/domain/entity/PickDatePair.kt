package kr.co.dooribon.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// 선택된 2개의 데이터 , 시작 날짜 , 끝 날짜 2개를 담아줄 데이터 클래스
@Parcelize
data class PickDatePair(
    val startDate: String,
    val endDate: String
) : Parcelable {
    companion object {
        val EMPTY = PickDatePair("", "")
    }
}
