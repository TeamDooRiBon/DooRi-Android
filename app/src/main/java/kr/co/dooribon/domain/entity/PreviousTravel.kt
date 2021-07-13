package kr.co.dooribon.domain.entity

data class PreviousTravel(
    val previousTravelId: String,
    val previousTripImageUrl: String,
    val previousTravelDate: String,
    val previousTravelTitle: String,
    val previousTravelPlace: String,
    val previousTravelPeople: Int
)
