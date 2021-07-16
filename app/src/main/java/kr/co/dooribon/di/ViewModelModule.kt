package kr.co.dooribon.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.application.MainApplication.Companion.repositoryModule
import kr.co.dooribon.ui.factory.*

/**
 * ViewModelFactory를 주입시켜주는 Module
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
}