package com.senpro.ulamsae.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senpro.ulamsae.data.SettingsRepository
import com.senpro.ulamsae.model.Market

class HistoryViewModel (private val repository: SettingsRepository) : ViewModel() {
    val listMarket: LiveData<List<Market>> =
        repository.getAllFish()
}