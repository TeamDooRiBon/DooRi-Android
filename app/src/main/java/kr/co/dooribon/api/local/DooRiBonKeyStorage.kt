package kr.co.dooribon.api.local

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import kr.co.dooribon.BuildConfig

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

    var jwtToken: String
        set(value) = authStorage.edit(commit = true) { putString(JWT_TOKEN, value) }
        get() = authStorage.getString(JWT_TOKEN, "") ?: ""

    var userId: String
        set(value) = authStorage.edit(commit = true) { putString(USER_ID, value) }
        get() = authStorage.getString(USER_ID, "") ?: ""

    var userName: String
        set(value) = authStorage.edit(commit = true) { putString(USER_NAME, value) }
        get() = authStorage.getString(USER_NAME, "") ?: ""

    var userEmail: String
        set(value) = authStorage.edit(commit = true) { putString(USER_EMAIL, value) }
        get() = authStorage.getString(USER_EMAIL, "") ?: ""

    var profileImageUrl: String
        set(value) = authStorage.edit(commit = true) { putString(PROFILE_IMAGE_URL, value) }
        get() = authStorage.getString(PROFILE_IMAGE_URL, "") ?: ""

    companion object {
        private const val FILE_NAME = "DOORIBON_AUTH"
        private const val DEBUG_FILE_NAME = "DEBUG_DOORIBON_AUTH"
        private const val ACCESS_TOKEN = "ACCESS_TOKEN"
        private const val REFRESH_TOKEN = "REFRESH_TOKEN"
        private const val JWT_TOKEN = "JWT_TOKEN"
        private const val USER_ID = "USER_ID"
        private const val USER_NAME = "USER_NAME"
        private const val USER_EMAIL = "USER_EMAIL"
        private const val PROFILE_IMAGE_URL = "PROFILE_IMAGE_URL"
    }
}