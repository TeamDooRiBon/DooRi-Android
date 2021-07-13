package kr.co.dooribon.api.remote

import kr.co.dooribon.domain.entity.PreviousTravel
import kr.co.dooribon.domain.entity.Travel
import kr.co.dooribon.domain.entity.UpComingTravel
import kr.co.dooribon.utils.DateUtil

/**
 * Domain Layout 객체로 변경해주는 확장 함수 모음
 */
fun TravelDTO.asDomainTravel(): Travel {
    return Travel(
        id = this.id,
        travelStartDate = startDate,
        travelEndDate = endDate,
        travelTitle = travelTitle,
        travelThumbnailUrl = travelThumbnailUrl,
        travelDestination = travelDestination,
        travelMembers = travelMembers
    )
}

fun TravelDTO.asDomainUpComingTravel(): UpComingTravel {
    return UpComingTravel(
        upComingTravelId = this.id,
        upComingTravelImageUrl = this.travelThumbnailUrl,
        upComingTravelStartDate = this.startDate,
        upComingTravelEndDate = this.endDate,
        upComingTravelLocation = this.travelDestination,
        upComingTravelPersonCount = this.travelMembers.size,
        upComingTravelTitle = this.travelTitle,
        upComingTravelDday = DateUtil.countDday(DateUtil.convertStringToDate(startDate)).toInt()
    )
}

fun TravelDTO.asDomainPreviousTravel(): PreviousTravel {
    return PreviousTravel(
        previousTravelId = this.id,
        previousTravelPeople = this.travelMembers.size,
        previousTravelDate = this.startDate,
        previousTravelPlace = this.travelDestination,
        previousTravelTitle = this.travelTitle,
        previousTripImageUrl = this.travelThumbnailUrl
    )
}

fun List<TravelDTO>.asDomainListTravel(): List<Travel> {
    return map {
        it.asDomainTravel()
    }
}

fun List<TravelDTO>.asDomainUpComingTravel(): List<UpComingTravel> {
    return map {
        it.asDomainUpComingTravel()
    }
}

fun List<TravelDTO>.asDomainPreviousTravel() : List<PreviousTravel> {
    return map {
        it.asDomainPreviousTravel()
    }
}