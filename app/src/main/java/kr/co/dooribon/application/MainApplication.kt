package kr.co.dooribon.application

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import kr.co.dooribon.R
import kr.co.dooribon.di.*
import kr.co.dooribon.utils.PixelRatio

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeSingleton()

        KakaoSdk.init(this,getString(R.string.kakao_app_key))
    }

    private fun initializeSingleton() {
        pixelRatio = PixelRatio(this)
        keyStorageModule = KeyStorageModule(this)
        retrofitModule = RetrofitModule()
        viewModelModule = ViewModelModule()
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
        lateinit var keyStorageModule: KeyStorageModule
    }
}