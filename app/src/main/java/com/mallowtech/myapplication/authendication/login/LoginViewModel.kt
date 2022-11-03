package com.mallowtech.myapplication.authendication.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.todoapp.api.models.login.LoginError
import com.example.todoapp.api.models.login.LoginResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mallowtech.myapplication.R
import com.mallowtech.myapplication.api.ApiManager
import com.mallowtech.myapplication.commonFiles.HRFunctions.checkPassword
import com.mallowtech.myapplication.commonFiles.HRFunctions.ifBothNotNull
import com.mallowtech.myapplication.commonFiles.HRFunctions.isValidUsername
import com.mallowtech.myapplication.commonFiles.MyApplication
import com.mallowtech.myapplication.commonFiles.SingleLiveEvent
import com.mallowtech.myapplication.model.UserDetails
import com.mallowtech.myapplication.model.login.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var username: String? = null
    var password: String? = null
    val onLoginSuccess by lazy { SingleLiveEvent<Boolean>() }
    val validationErrorUsername by lazy { SingleLiveEvent<Int>() }
    val validationErrorPassword by lazy { SingleLiveEvent<Int>() }
    val loginErrorResponse by lazy { SingleLiveEvent<String>() }


    fun doLogin() {
        if (checkInputValidation()) {
            login()
        }
    }

    private fun login() {
        ifBothNotNull(username, password) { email, password ->
            ApiManager.unAuthorisedClient.loginUser(User(UserDetails(email, password)))
                .enqueue(object : Callback<LoginResponse?> {
                    override fun onResponse(
                        call: Call<LoginResponse?>,
                        response: Response<LoginResponse?>
                    ) {
                        if (response.isSuccessful) {
                            val pref = MyApplication.getInstance().myPrefs()
                            pref.email = email
                            pref.password = password
                            onLoginSuccess.value = true
                        } else {
                            if (response.code() in 300..499 && response.errorBody() != null) {
                                val gson = Gson()
                                val type = object : TypeToken<LoginError>() {}.type
                                val errorResponse: LoginError =
                                    gson.fromJson(response.errorBody()!!.charStream(), type)

                                loginErrorResponse.value = errorResponse.error
                            } else {
                                loginErrorResponse.value = response.code().toString()
                            }
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse?>, t: Throwable) {
                        loginErrorResponse.value = "On Failure :" + t.message

                    }
                })
        }
    }

    private fun checkInputValidation(): Boolean {
        var errorEmail: Int? = null
        when {
            username.isNullOrEmpty() -> errorEmail = R.string.email_empty
            !isValidUsername(username!!) -> errorEmail = R.string.email_format_error
        }
        errorEmail?.let {
            validationErrorUsername.value = it
            return false
        }
        checkPassword(password)?.let {
            validationErrorPassword.value = it
            return false
        }
        return true
    }
}