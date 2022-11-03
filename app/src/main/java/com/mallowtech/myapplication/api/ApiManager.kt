package com.mallowtech.myapplication.api

import com.google.gson.GsonBuilder
import com.mallowtech.myapplication.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {

    companion object {

        private const val KEY_CONTENT_TYPE = "Content-Type"
        private const val APPLICATION_JSON = "application/json"
        private const val AUTHORISATION_KEY = "Authorization"
        var AUTH: String? = null

        val unAuthorisedClient: ApiInterface by lazy {
            buildRetrofit(unAuthorisedOkHttpClient).create(ApiInterface::class.java)
        }

        private val unAuthorisedOkHttpClient by lazy {
            generateOkHttpClient(isAuthorisedClient = false)
        }
        val AuthorisedClient: ApiInterface by lazy {
            buildRetrofit(AuthorisedOkHttpClient).create(ApiInterface::class.java)
        }

        private val AuthorisedOkHttpClient by lazy {
            generateOkHttpClient(isAuthorisedClient = true)
        }

        private fun buildRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder().apply {
            baseUrl(Constants.BASEURL)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            client(okHttpClient)
        }.build()

        private fun generateOkHttpClient(isAuthorisedClient: Boolean): OkHttpClient =
            OkHttpClient().newBuilder().apply {
                addInterceptor { chain ->
                    chain.proceed(getRequest(chain.request(), isAuthorisedClient))
                }
                if (BuildConfig.DEBUG) {
                    addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                }
            }.build()

        private fun getRequest(request: Request, authorisedClient: Boolean): Request =
            request.newBuilder().apply {
                header(KEY_CONTENT_TYPE, APPLICATION_JSON)
                if (authorisedClient) {
                    AUTH?.let { auth -> header(AUTHORISATION_KEY, auth) }
                }
            }.build()
    }


}

