package kr.co.dooribon.application

import android.app.Application
import kr.co.dooribon.di.*
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
        sharedPreferenceModule = SharedPreferenceModule(this)
        apiModule = ApiModule()
        repositoryModule = RepositoryModule()
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
        lateinit var retrofitModule: RetrofitModule
        lateinit var viewModelModule: ViewModelModule
        lateinit var sharedPreferenceModule: SharedPreferenceModule
        lateinit var apiModule: ApiModule
        lateinit var repositoryModule: RepositoryModule
    }
}