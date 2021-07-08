package kr.co.dooribon.application

import android.app.Application
import kr.co.dooribon.di.LocalDatabaseModule
import kr.co.dooribon.di.RepositoryModule
import kr.co.dooribon.di.RetrofitModule
import kr.co.dooribon.di.ViewModelModule
import kr.co.dooribon.utils.PixelRatio

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeSingleton()
    }

    private fun initializeSingleton() {
        pixelRatio = PixelRatio(this)
        retrofitModule = RetrofitModule()
        viewModelModule = ViewModelModule(this)
        repositoryModule = RepositoryModule()
        localDatabaseModule = LocalDatabaseModule(this)
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
        lateinit var retrofitModule: RetrofitModule
        lateinit var viewModelModule: ViewModelModule
        lateinit var repositoryModule: RepositoryModule
        lateinit var localDatabaseModule: LocalDatabaseModule
    }
}