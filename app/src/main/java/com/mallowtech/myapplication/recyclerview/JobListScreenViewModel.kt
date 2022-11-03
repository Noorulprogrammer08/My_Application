package com.mallowtech.myapplication.recyclerview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.api.models.login.LoginError
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mallowtech.myapplication.api.ApiManager
import com.mallowtech.myapplication.commonFiles.SingleLiveEvent
import com.mallowtech.myapplication.model.jobList.JobListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class JobListScreenViewModel(application: Application) : AndroidViewModel(application) {
    val errorMessage by lazy { SingleLiveEvent<String>() }
    val passIntent by lazy { SingleLiveEvent<Boolean>() }

    fun getTasks() {
        ApiManager.AuthorisedClient.jobList().enqueue(object : Callback<JobListResponse?> {
            override fun onResponse(
                call: Call<JobListResponse?>,
                response: Response<JobListResponse?>
            ) {
                if (response.isSuccessful) {
                    response.body()?.jobList?.let {
                        JobListAdapter.jobAdapter.setData(it)
                    }
                    passIntent.value = true
                } else {
                    if (response.code() in 300..499 && response.errorBody() != null) {
                        val gson = Gson()
                        val type = object : TypeToken<LoginError>() {}.type
                        val errorResponse: LoginError =
                            gson.fromJson(response.errorBody()!!.charStream(), type)
                        errorMessage.postValue(errorResponse.error)
                    } else {
                        errorMessage.postValue(response.code().toString())
                    }
                }
            }

            override fun onFailure(call: Call<JobListResponse?>, t: Throwable) {
                errorMessage.postValue("OnFailure" + t.message)
            }
        })
    }

}