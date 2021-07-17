package kr.co.dooribon.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.application.MainApplication.Companion.repositoryModule
import kr.co.dooribon.ui.existingtrip.tendency.viewmodel.MemberViewModel
import kr.co.dooribon.ui.factory.*

/**
 * ViewModelFactory를 주입시켜주는 Module
 *
 * TODO : Lazy로 만들어서 캐시할 수 있도록 해야할거 같음!!!
 */
class ViewModelModule(private val application: Application) {

    fun provideHomeViewModelFactory(): ViewModelProvider.Factory =
        HomeViewModelFactory(repositoryModule.homeRepository)

    fun provideTripTendencyViewModelFactory(): ViewModelProvider.Factory =
        TripTendencyViewModelFactory(repositoryModule.tripTendencyRepository)

    fun provideTendencyViewModelFactory(): ViewModelProvider.Factory =
        TendencyViewModelFactory(repositoryModule.tripTendencyRepository)

    fun provideMemberViewModelFactory(): ViewModelProvider.Factory =
        MemberViewModelFactory(repositoryModule.tripTendencyRepository)

    fun provideDetailViewModelFactory(): ViewModelProvider.Factory =
        DetailViewModelFactory(repositoryModule.tripTendencyRepository)

    fun provideParticipateGroupViewModelFactory(): ViewModelProvider.Factory =
        ParticipateGroupViewModelFactory(repositoryModule.participateGroupRepository)

    val homeViewModelFactory by lazy {
        HomeViewModelFactory(repositoryModule.homeRepository)
    }

    val tripTendencyViewModelFactory by lazy {
        TripTendencyViewModelFactory(repositoryModule.tripTendencyRepository)
    }

    val memberViewModelFactory by lazy {
        MemberViewModelFactory(repositoryModule.tripTendencyRepository)
    }

    val detailViewModelFactory by lazy {
        DetailViewModelFactory(repositoryModule.tripTendencyRepository)
    }

    val participateGroupViewModelFactory by lazy {
        ParticipateGroupViewModelFactory(repositoryModule.participateGroupRepository)
    }
}