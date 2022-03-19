package kr.co.dooribon.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * SharedPrefs나 Room과 같은 걸 사용해야할 떄
 */
class SharedPreferenceModule(private val application: Application) {

    private val sharedPreference: SharedPreferences by lazy {
        application.getSharedPreferences(SP_NAME, MODE_PRIVATE)
    }

    var isFirstLaunch: Boolean
        get() {
            val result = sharedPreference.getBoolean(IS_FIRST_LAUNCH, true)
            if (result) {
                sharedPreference.edit().putBoolean(IS_FIRST_LAUNCH, false).apply()
            }
            return result
        }
        set(_) {
            sharedPreference.edit().putBoolean(IS_FIRST_LAUNCH, false).apply()
        }

    var isFirstAuthLaunch: Boolean
        get() {
            val result = sharedPreference.getBoolean(IS_FIRST_AUTH_LAUNCH, true)
            if (result) {
                sharedPreference.edit().putBoolean(IS_FIRST_AUTH_LAUNCH,false).apply()
            }
            return result
        }
        set(_) {
            sharedPreference.edit().putBoolean(IS_FIRST_AUTH_LAUNCH,false).apply()
        }

    companion object {
        private const val SP_NAME = "SHARED_PREFERENCE"
        private const val IS_FIRST_LAUNCH = "isFirstLaunch"
        private const val IS_FIRST_AUTH_LAUNCH = "IS_FIRST_AUTH_LAUNCH"
    }
} 