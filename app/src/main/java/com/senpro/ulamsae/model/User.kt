package com.senpro.ulamsae.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    @field:SerializedName("username")
    var username: String = "uname",

    @field:SerializedName("email")
    var email: String = "mail",

//    @field:SerializedName("image")
//    var image: String = "url",

    @field:SerializedName("token")
    var token: String = "0"
) : Parcelable