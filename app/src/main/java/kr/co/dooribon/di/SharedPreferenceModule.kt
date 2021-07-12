package kr.co.dooribon.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

/**
 * SharedPrefs나 Room과 같은 걸 사용해야할 떄
 */
class SharedPreferenceModule(private val application: Application) {

    val sharedPreference: SharedPreferences by lazy {
        application.getSharedPreferences(SP_NAME, MODE_PRIVATE)
    }

    init {
        setJwtToken()
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

    val jwtToken by lazy {
        sharedPreference.getString(JWT_TOKEN_KEY, "").toString()
    }

    private fun setJwtToken() {
        sharedPreference.edit().putString(JWT_TOKEN_KEY, JWT_TOKEN).apply()
    }

    companion object {
        private const val SP_NAME = "SHARED_PREFERENCE"
        private const val IS_FIRST_LAUNCH = "isFirstLaunch"

        private const val JWT_TOKEN_KEY = "jwtTokenKey"
        private const val JWT_TOKEN =
            "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoiNjBlYzY5MjU2MmU3YzhiYzU4YWEwMjM0In0sImlhdCI6MTYyNjEwNjE0OSwiZXhwIjoxNjI2NDY2MTQ5fQ.m-ehqhIQtasePYkx2AUhz6o25T5GFCRePS8t-hbEgAc"
    }
} 