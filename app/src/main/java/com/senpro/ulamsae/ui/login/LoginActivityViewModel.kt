package com.senpro.ulamsae.ui.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senpro.ulamsae.api.ApiConfig
import com.senpro.ulamsae.model.Settings
import com.senpro.ulamsae.data.SettingsRepository
import com.senpro.ulamsae.model.User
import com.senpro.ulamsae.model.request.LoginRequest
import com.senpro.ulamsae.model.response.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivityViewModel(private val repository: SettingsRepository) : ViewModel() {

    fun saveSession(user: User) {
        repository.setLogin(true, user)
    }

    fun login(loginRequest: LoginRequest): LiveData<Boolean> {
        val loginLiveData = MutableLiveData<Boolean>()
        val client = ApiConfig().getApiService().login(loginRequest)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()?.data
                    if (responseBody != null) {
//                        Log.d("message", responseBody.accessToken)
//                        showToast(response.)
                        val newUser = User(
                            loginRequest.username, loginRequest.password, responseBody.accessToken
                        )
                        saveSession(newUser)
                        loginLiveData.value = true
                        return
                    }
                }
//                showToast(response.message())
                loginLiveData.value = false
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                t.message?.let { showToast(it) }
                loginLiveData.value = false
            }
        })
        return loginLiveData
    }

//    fun showToast(message: String) {
//        val toast: Toast = Toast.makeText(, message, Toast.LENGTH_SHORT)
//        toast.show()
//    }
}