package com.senpro.ulamsae.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senpro.ulamsae.api.ApiConfig
import com.senpro.ulamsae.data.SettingsRepository
import com.senpro.ulamsae.model.Settings
import com.senpro.ulamsae.model.response.User
import com.senpro.ulamsae.model.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel(private val repository: SettingsRepository) : ViewModel() {

    private val _user = MutableLiveData<User?>(null)
    val user : LiveData<User?> get() = _user

    fun getSettings() {
        val settings = repository.getSettings()
        setUserDetail(settings.userID)
    }

    private fun setUserDetail(username: String) {
        val client = ApiConfig().getApiService().getUserDetail(username)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    _user.value = response.body()?.data?.user
                } else {
//                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
}