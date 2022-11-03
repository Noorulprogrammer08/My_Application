package com.mallowtech.myapplication.api

import com.example.todo_application.Model.Signup.response.SignupResponse
import com.example.todoapp.api.models.login.LoginResponse
import com.mallowtech.myapplication.model.jobList.JobListResponse
import com.mallowtech.myapplication.model.login.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST(value = "users/sign_up.json")
    fun signupUser(
        @Body user: User
    ): Call<SignupResponse>

    @POST(value = "users/log_in.json")
    fun loginUser(
        @Body user: User
    ): Call<LoginResponse>

    @GET(value = "todos.json")
    fun jobList(): Call<JobListResponse>

}
