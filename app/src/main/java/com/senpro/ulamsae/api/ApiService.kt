package com.senpro.ulamsae.api

import com.senpro.ulamsae.model.request.LoginRequest
import com.senpro.ulamsae.model.request.RegisterRequest
import com.senpro.ulamsae.model.response.LoginResponse
import com.senpro.ulamsae.model.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("authentications")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("users")
    fun register(
        @Body registerRequest: RegisterRequest
    ): Call<RegisterResponse>
}