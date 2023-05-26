package com.senpro.ulamsae.di

import android.content.Context
import com.senpro.ulamsae.data.SettingsRepository

object Injection {
    fun provideRepository(context: Context): SettingsRepository {
//        val apiService = ApiConfig.getApiService(context)
        return SettingsRepository(context)
    }
}