package kr.co.dooribon.application

import android.app.Application
import kr.co.dooribon.di.Injection
import kr.co.dooribon.utils.PixelRatio

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeSingleton()
    }

    private fun initializeSingleton() {
        pixelRatio = PixelRatio(this)
        injection = Injection(this)
    }

    companion object {
        lateinit var pixelRatio: PixelRatio
        lateinit var injection : Injection
    }
}