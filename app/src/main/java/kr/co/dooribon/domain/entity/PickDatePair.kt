package kr.co.dooribon.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class PickDatePair(
    val startDate : String,
    val endDate : String
) : Parcelable
