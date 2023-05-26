package com.senpro.ulamsae.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Settings(
    var isDarkMode: Int = 2,
    var isLogin: Boolean = false,
) : Parcelable
