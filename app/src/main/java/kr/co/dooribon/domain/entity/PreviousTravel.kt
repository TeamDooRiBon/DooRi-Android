package kr.co.dooribon.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreviousTravel(
    val previousTravelId: String,
    val previousTripImageUrl: String,
    val previousTravelDate: String,
    val previousTravelTitle: String,
    val previousTravelPlace: String,
    val previousTravelPeople: Int
) : Parcelable
