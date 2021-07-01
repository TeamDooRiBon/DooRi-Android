package kr.co.dooribon.domain.entity

data class UpComingTrip(
    val upComingTripImageUrl : String,
    val upComingTripDday : Int,
    val upComingTripTitle : String,
    val upComingTripStartDate : String,
    val upComingTripEndDate : String,
    val upComingTripLocation : String,
    val upComingTripPersonCount : Int
)
