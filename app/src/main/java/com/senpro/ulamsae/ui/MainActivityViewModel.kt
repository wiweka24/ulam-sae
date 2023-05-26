package com.senpro.ulamsae.ui

import androidx.lifecycle.ViewModel
import com.senpro.ulamsae.model.Settings
import com.senpro.ulamsae.data.SettingsRepository

class MainActivityViewModel(private val repository: SettingsRepository) : ViewModel() {

    fun getCurrentSettings(): Settings {
        return repository.getSettings()
    }
}