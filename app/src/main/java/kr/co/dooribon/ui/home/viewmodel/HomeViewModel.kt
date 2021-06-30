package kr.co.dooribon.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import kr.co.dooribon.domain.entity.UpComingTrip

class HomeViewModel : ViewModel() {

    val upComingTripDummyList = listOf<UpComingTrip>(
        UpComingTrip("1",4,"살려줘","2020.06.02","06.21","인천 남동구",4),
        UpComingTrip("1",5,"살려줘","2020.06.02","06.25","인천 남동구",5),
        UpComingTrip("1",6,"살려줘","2020.06.02","06.28","인천 남동구",6),
        UpComingTrip("1",7,"살려줘","2020.06.02","06.30","인천 남동구",7),
        UpComingTrip("1",78,"살려줘","2020.06.02","06.31","인천 남동구",8)
    )
}