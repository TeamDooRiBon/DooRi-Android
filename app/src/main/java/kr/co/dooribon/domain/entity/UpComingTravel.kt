package kr.co.dooribon.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpComingTravel(
    val upComingTravelId: String,
    val upComingTravelImageUrl: String,
    val upComingTravelDday: Int,
    val upComingTravelTitle: String,
    val upComingTravelStartDate: String,
    val upComingTravelEndDate: String,
    val upComingTravelLocation: String,
    val upComingTravelPersonCount: Int
) : Parcelable
