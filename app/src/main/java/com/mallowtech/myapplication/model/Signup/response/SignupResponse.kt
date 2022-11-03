package com.example.todo_application.Model.Signup.response

import com.google.gson.annotations.SerializedName

data class SignupResponse(
    @SerializedName("create")
    val createdAt: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("update")
    val updatedAt: String
)