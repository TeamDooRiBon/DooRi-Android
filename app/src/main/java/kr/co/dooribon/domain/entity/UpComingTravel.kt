package kr.co.dooribon.domain.entity

data class UpComingTravel(
    val upComingTravelId : String,
    val upComingTravelImageUrl: String,
    val upComingTravelDday: Int,
    val upComingTravelTitle: String,
    val upComingTravelStartDate: String,
    val upComingTravelEndDate: String,
    val upComingTravelLocation: String,
    val upComingTravelPersonCount: Int
)
