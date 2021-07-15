package kr.co.dooribon.api.remote

import kr.co.dooribon.domain.entity.*
import kr.co.dooribon.utils.DateUtil
import java.time.DateTimeException

/**
 * Domain Layout 객체로 변경해주는 확장 함수 모음
 */
// Proceeding Travel
fun TravelDTO.asDomainTravel(): Travel {
    return Travel(
        id = this.id,
        travelStartDate = DateUtil.convertStringToStringWithOutTime(this.startDate),
        travelEndDate = DateUtil.convertStringToStringWithOutTime(this.endDate),
        travelTitle = this.travelTitle,
        travelThumbnailUrl = this.travelThumbnailUrl,
        travelDestination = this.travelDestination,
        travelMembers = this.travelMembers
    )
}

// Upcoming Travel
fun TravelDTO.asDomainUpComingTravel(): UpComingTravel {
    return UpComingTravel(
        upComingTravelId = this.id,
        upComingTravelImageUrl = this.travelThumbnailUrl,
        upComingTravelStartDate = DateUtil.convertStringToStringWithOutTime(this.startDate),
        upComingTravelEndDate = DateUtil.convertStringToStringWithOutTime(this.endDate),
        upComingTravelLocation = this.travelDestination,
        upComingTravelPersonCount = this.travelMembers.size,
        upComingTravelTitle = this.travelTitle,
        upComingTravelDday = DateUtil.countDday(DateUtil.convertStringToDate(startDate)).toInt()
    )
}

// Previous Travel
fun TravelDTO.asDomainPreviousTravel(): PreviousTravel {
    return PreviousTravel(
        previousTravelId = this.id,
        previousTravelPeople = this.travelMembers.size,
        previousTravelDate = DateUtil.convertStringToStringWithOutTime(this.startDate),
        previousTravelPlace = this.travelDestination,
        previousTravelTitle = this.travelTitle,
        previousTripImageUrl = this.travelThumbnailUrl
    )
}