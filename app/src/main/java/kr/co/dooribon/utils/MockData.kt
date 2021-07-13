package kr.co.dooribon.utils

import kr.co.dooribon.domain.entity.PreviousTravel
import kr.co.dooribon.domain.entity.Travel
import kr.co.dooribon.domain.entity.UpComingTravel

object MockData {
    fun provideUpComingData() = listOf(
        UpComingTravel("1", "1", 1, "1", "1", "1", "1", 4),
        UpComingTravel("1", "1", 1, "1", "1", "1", "1", 4)
    )

    fun providePreviousData() = listOf(
        PreviousTravel("1", "1", "1", "1", "1", 4)
    )

    fun provideHomeData() =
        Travel(
            "1", "Nothing", "Nothing", "Nothing", "123", "Nothing",
            listOf()
        )

}