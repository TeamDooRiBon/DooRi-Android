package kr.co.dooribon.api.remote

import kr.co.dooribon.domain.entity.Travel

/**
 * Domain Layout 객체로 변경해주는 확장 함수 모음
 */
fun TravelDTO.asDomainTravel() : Travel {
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

fun List<TravelDTO>.asDomainListTravel() : List<Travel> {
    return map {
        it.asDomainTravel()
    }
}