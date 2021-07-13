package kr.co.dooribon.di

import kr.co.dooribon.api.remote.*
import kr.co.dooribon.application.MainApplication.Companion.retrofitModule

class ApiModule {

    val travelApi by lazy {
        retrofitModule.createApi(TravelAPI::class)
    }

    val scheduleApi by lazy {
        retrofitModule.createApi(ScheduleAPI::class)
    }

    val travelImageApi by lazy {
        retrofitModule.createApi(TravelImageAPI::class)
    }

    val boardApi by lazy {
        retrofitModule.createApi(BoardAPI::class)
    }

    val tendencyApi by lazy {
        retrofitModule.createApi(TendencyAPI::class)
    }
}