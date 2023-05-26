package com.senpro.ulamsae.data

import android.content.Context
import android.provider.Telephony.Carriers.PASSWORD
import com.senpro.ulamsae.model.Settings
import com.senpro.ulamsae.model.User

class SettingsRepository(context: Context)  {
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setDarkMode(value: Int) {
        val editor = preferences.edit()
        editor.putInt(STATE_DARK_MODE, value)
        editor.apply()
    }

    fun setLogin(state: Boolean, user: User) {
        val editor = preferences.edit()
        editor.putBoolean(STATE_LOGIN, state)
        editor.putString(USERNAME, user.username)
        editor.putString(EMAIL, user.email)
        editor.putString(TOKEN, user.token)
//        editor.putString(URL, user.image)
        editor.apply()
    }

    fun getSettings(): Settings {
        val model = Settings()
        model.isDarkMode = preferences.getInt(STATE_DARK_MODE, 2)
        model.isLogin = preferences.getBoolean(STATE_LOGIN, false)
        return model
    }

    fun getUser(): User {
        val model = User()
        model.username = preferences.getString(USERNAME, "username").toString()
        model.email = preferences.getString(EMAIL, "email").toString()
//        model.image = preferences.getString(URL, "url").toString()
        model.token = preferences.getString(TOKEN, "token").toString()
        return model
    }

    fun clearSession() {
        val editor = preferences.edit()
        editor.remove("login")
        editor.remove("username")
        editor.remove("email")
        editor.remove("token")
//        editor.remove("url")
        editor.apply()
    }

    companion object {
        private const val PREFS_NAME = "settings_pref"
        private const val STATE_DARK_MODE = "dark_mode"
        private const val STATE_LOGIN = "login"
        private const val USERNAME = "username"
        private const val EMAIL = "email"
        private const val TOKEN = "token"
//        private const val URL = "url"
    }
}