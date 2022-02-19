package kr.co.dooribon.api.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kr.co.dooribon.BuildConfig

/**
 * 
 * TODO by SSong-develop kakaoUserToken , ~~~~ 필요한거 챙기기
 */
class DooRiBonKeyStorage(
    private val context: Context
) {
    private val authStorage = if (!BuildConfig.DEBUG) EncryptedSharedPreferences.create(
        FILE_NAME,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    ) else {
        context.getSharedPreferences(DEBUG_FILE_NAME, Context.MODE_PRIVATE)
    }

    var accessToken: String
        set(value) = authStorage.edit(commit = true) { putString(ACCESS_TOKEN, value) }
        get() = authStorage.getString(ACCESS_TOKEN, "") ?: ""

    var refreshToken: String
        set(value) = authStorage.edit(commit = true) { putString(REFRESH_TOKEN, value) }
        get() = authStorage.getString(REFRESH_TOKEN, "") ?: ""


    companion object {
        private const val FILE_NAME = "DOORIBON_AUTH"
        private const val DEBUG_FILE_NAME = "DEBUG_DOORIBON_AUTH"

        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
    }
}