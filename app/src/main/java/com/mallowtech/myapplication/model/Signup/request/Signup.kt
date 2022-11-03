package com.example.todo_application.Model.Signup.request

import com.google.gson.annotations.SerializedName
import com.mallowtech.myapplication.model.login.User

data class Signup(
    @SerializedName("user")
    val user: User
)