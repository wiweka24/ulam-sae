package com.senpro.ulamsae.ui.profile

import androidx.lifecycle.ViewModel
import com.senpro.ulamsae.data.SettingsRepository
import com.senpro.ulamsae.model.Settings
import com.senpro.ulamsae.model.User

class ProfileViewModel(private val repository: SettingsRepository) : ViewModel() {

    fun getCurrentUser(): User {
        return repository.getUser()
    }
}