package com.senpro.ulamsae.ui.setting

import androidx.lifecycle.ViewModel
import com.senpro.ulamsae.model.Settings
import com.senpro.ulamsae.data.SettingsRepository

class SettingViewModel(private val repository: SettingsRepository) : ViewModel() {

    fun getCurrentSettings(): Settings {
        return repository.getSettings()
    }

    fun setDarkMode(state: Int) {
        repository.setDarkMode(state)
    }

    fun removeSession() {
        repository.clearSession()
    }
}