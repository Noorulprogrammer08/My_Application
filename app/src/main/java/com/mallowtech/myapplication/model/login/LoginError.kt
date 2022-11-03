package com.example.todoapp.api.models.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginError(
    @SerializedName("error")
    @Expose
    val error: String
)