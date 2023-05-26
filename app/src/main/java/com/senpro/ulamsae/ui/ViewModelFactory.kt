package com.senpro.ulamsae.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.senpro.ulamsae.di.Injection
import com.senpro.ulamsae.ui.login.LoginActivityViewModel
import com.senpro.ulamsae.ui.profile.ProfileViewModel
import com.senpro.ulamsae.ui.register.RegisterActivityViewModel
import com.senpro.ulamsae.ui.setting.SettingViewModel

class ViewModelFactory(private val context: Context) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(Injection.provideRepository(context)) as T
        }
        else if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(Injection.provideRepository(context)) as T
        }
        else if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(Injection.provideRepository(context)) as T
        }
        else if (modelClass.isAssignableFrom(RegisterActivityViewModel::class.java)) {
            return RegisterActivityViewModel(Injection.provideRepository(context)) as T
        }
        else if (modelClass.isAssignableFrom(LoginActivityViewModel::class.java)) {
            return LoginActivityViewModel(Injection.provideRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}