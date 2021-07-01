package kr.co.dooribon.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import kr.co.dooribon.domain.entity.PreviousTrip
import kr.co.dooribon.domain.entity.UpComingTrip

/**
 * Repository 넣어줘야 하니까 factory도 필요함
 */
class HomeViewModel : ViewModel() {

    val upComingTripDummyList = listOf<UpComingTrip>(
        UpComingTrip("1", 4, "살려줘", "2020.06.02", "06.21", "인천 남동구", 4),
        UpComingTrip("1", 5, "살려줘", "2020.06.02", "06.25", "인천 남동구", 5),
        UpComingTrip("1", 6, "살려줘", "2020.06.02", "06.28", "인천 남동구", 6),
        UpComingTrip("1", 7, "살려줘", "2020.06.02", "06.30", "인천 남동구", 7),
        UpComingTrip("1", 78, "살려줘", "2020.06.02", "06.31", "인천 남동구", 8)
    )

    val previousTripDummyList = listOf(
        PreviousTrip("1", "2020.05", "또 살려주면 안돼?", "서울 홍대", 4),
        PreviousTrip("1", "2020.06", "또 살려주면 안돼?!", "서울 홍대", 7),
        PreviousTrip("1", "2020.07", "또 살려주면 안돼?!!", "서울 홍대", 9)
    )
}