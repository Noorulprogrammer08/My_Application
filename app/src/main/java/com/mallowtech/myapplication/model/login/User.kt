package com.mallowtech.myapplication.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mallowtech.myapplication.model.UserDetails

data class User(
    @SerializedName("user")
    @Expose
    val userDetails: UserDetails
)