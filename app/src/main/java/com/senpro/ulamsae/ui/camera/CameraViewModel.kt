package com.senpro.ulamsae.ui.camera

import androidx.lifecycle.ViewModel
import com.senpro.ulamsae.data.SettingsRepository
import com.senpro.ulamsae.model.Market
import java.io.File

class CameraViewModel (private val repository: SettingsRepository) : ViewModel() {

    fun addFish(nama: String, harga:String, file: File) {
        repository.addFish(nama, harga, file)
    }
}