package kr.co.dooribon.di

import android.app.Application
import kr.co.dooribon.api.local.DooRiBonKeyStorage

class KeyStorageModule(
    private val application: Application
) {
    val dooRiBonKeyStorage by lazy {
        DooRiBonKeyStorage(application)
    }
}