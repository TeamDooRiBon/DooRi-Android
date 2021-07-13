package kr.co.dooribon.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import kr.co.dooribon.application.MainApplication.Companion.repositoryModule
import kr.co.dooribon.ui.factory.HomeViewModelFactory
import kr.co.dooribon.ui.factory.TendencyViewModelFactory
import kr.co.dooribon.ui.factory.TripTendencyViewModelFactory

/**
 * ViewModelFactory를 주입시켜주는 Module
 */
class ViewModelModule(private val application: Application) {

    fun provideHomeViewModelFactory(): ViewModelProvider.Factory =
        HomeViewModelFactory(repositoryModule.homeRepository)

    fun provideTripTendencyViewModelFactory(): ViewModelProvider.Factory =
        TripTendencyViewModelFactory()

    fun provideTendencyViewModelFactory(): ViewModelProvider.Factory =
        TendencyViewModelFactory()
}