package com.porto.anycook.framework.local.authentication

import android.content.SharedPreferences
import com.porto.core.data.authentication.source.AuthenticationLocalDataSource
import javax.inject.Inject

class AuthenticationLocalDataSourceImpl @Inject constructor(private val sharedPreferences : SharedPreferences)
    : AuthenticationLocalDataSource{

    companion object {
        private const val KEY_AUTH_TOKEN = "AuthenticationLocalDataSourceImpl.KeyAuthToken"
        private const val KEY_AUTHORIZED = "AuthenticationLocalDataSourceImpl.KeyAuthorized"
    }


    override fun setToken(token: String) {
        sharedPreferences.edit()
                .putString(KEY_AUTH_TOKEN, token)
                .apply()
    }

    override fun getToken(): String {
        return sharedPreferences.getString(KEY_AUTH_TOKEN, "").orEmpty()
    }

    override fun isAuthorized(): Boolean {
        return getToken().isNotEmpty()
    }

}