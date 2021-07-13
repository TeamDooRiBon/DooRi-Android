package kr.co.dooribon.api.remote

import kr.co.dooribon.domain.entity.DomainTravel

/**
 * Domain Layout 객체로 변경해주는 확장 함수 모음
 */
fun Travel.asDomainTravel() : DomainTravel {
    return DomainTravel(
        id = this.id,
        travelStartDate = startDate,
        travelEndDate = endDate,
        travelTitle = travelTitle,
        travelThumbnailUrl = travelThumbnailUrl,
        travelDestination = travelDestination,
        travelMembers = travelMembers
    )
}

fun List<Travel>.asDomainListTravel() : List<DomainTravel> {
    return map {
        it.asDomainTravel()
    }
}