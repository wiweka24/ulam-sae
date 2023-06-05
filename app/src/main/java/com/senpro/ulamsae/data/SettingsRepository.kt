package com.senpro.ulamsae.data

import android.content.Context
import android.provider.Telephony.Carriers.PASSWORD
import androidx.lifecycle.LiveData
import com.senpro.ulamsae.model.Market
import com.senpro.ulamsae.model.Settings
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class SettingsRepository(context: Context)  {
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val mMarketDao: MarketDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = MarketDatabase.getDatabase(context)
        mMarketDao = db.marketDao()
    }

    fun addFish(nama: String, harga:String, file: File) {
        executorService.execute {
            val newFish = Market(
                nama,
                harga,
                file
            )
            mMarketDao.addFish(newFish)
        }
    }

    fun getAllFish(): LiveData<List<Market>> = mMarketDao.getAllFish()

    fun setDarkMode(value: Int) {
        val editor = preferences.edit()
        editor.putInt(STATE_DARK_MODE, value)
        editor.apply()
    }

    fun setLogin(state: Boolean, token: String, refToken: String, id: String) {
        val editor = preferences.edit()
        editor.putBoolean(STATE_LOGIN, state)
        editor.putString(TOKEN, token)
        editor.putString(REF_TOKEN, refToken)
        editor.putString(USER_ID, id)
        editor.apply()
    }

    fun getSettings(): Settings {
        val model = Settings()
        model.isDarkMode = preferences.getInt(STATE_DARK_MODE, 2)
        model.isLogin = preferences.getBoolean(STATE_LOGIN, false)
        model.token = preferences.getString(TOKEN, "token").toString()
        model.refToken = preferences.getString(REF_TOKEN, "ref_token").toString()
        model.userID = preferences.getString(USER_ID, "user_id").toString()
        return model
    }

    fun clearSession() {
        val editor = preferences.edit()
        editor.remove("login")
        editor.remove("user_id")
        editor.remove("token")
        editor.remove("ref_token")
        editor.apply()
    }

    companion object {
        private const val PREFS_NAME = "settings_pref"
        private const val STATE_DARK_MODE = "dark_mode"
        private const val STATE_LOGIN = "login"
        private const val USER_ID = "user_id"
        private const val TOKEN = "token"
        private const val REF_TOKEN = "ref_token"
    }
}