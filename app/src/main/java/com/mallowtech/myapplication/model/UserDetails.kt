package com.mallowtech.myapplication.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDetails(
    @SerializedName("email")
    @Expose
    val email: String,
    @SerializedName("password")
    @Expose
    val password: String
)