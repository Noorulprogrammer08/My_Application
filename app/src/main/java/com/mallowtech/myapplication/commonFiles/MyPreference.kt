package com.mallowtech.myapplication.commonFiles

import android.content.Context
import android.content.SharedPreferences
import com.mallowtech.myapplication.api.Constants.CURRENT_USER
import com.mallowtech.myapplication.api.Constants.EMAIL
import com.mallowtech.myapplication.api.Constants.PASSWORD

class MyPreference(context: Context) {

    private var prefs: SharedPreferences =
        context.getSharedPreferences(CURRENT_USER, Context.MODE_PRIVATE)

    var email: String?
        get() = prefs.getString(EMAIL, null)
        set(mail) = prefs.edit().putString(EMAIL, mail).apply()

    var password: String?
        get() = prefs.getString(PASSWORD, null)
        set(password) = prefs.edit().putString(PASSWORD, password).apply()

    fun clear() {
        prefs.edit().clear().commit()
    }

}