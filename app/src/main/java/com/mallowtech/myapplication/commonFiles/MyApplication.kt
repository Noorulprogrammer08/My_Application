package com.mallowtech.myapplication.commonFiles

import android.app.Application

class MyApplication : Application() {
    companion object {
        private lateinit var myPreference: MyPreference
        private lateinit var currentInstance: MyApplication
        fun getInstance() = currentInstance
    }

    override fun onCreate() {
        super.onCreate()
        currentInstance = this
        myPreference = MyPreference(this)
    }

    fun myPrefs(): MyPreference = myPreference
}