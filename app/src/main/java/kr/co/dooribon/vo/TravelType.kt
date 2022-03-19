package kr.co.dooribon.vo

enum class TravelType(val type : String) {
    CURRENT_TRAVELS("nowTravels"), UPCOMING_TRAVELS("comeTravels"),ENDED_TRAVELS("endTravels")
}