package com.example.asjapp.retrofit

import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit: Retrofit
        get() {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttpClient: OkHttpClient =
                OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
<<<<<<< HEAD
                .baseUrl("https://ngo-drf-backend.herokuapp.com/")
=======
                .baseUrl("https://asj-ngo-api.herokuapp.com/")
>>>>>>> 3f1e1792612391dac10ef5f67bf6df23284cb42c
                .client(okHttpClient)
                .build()
        }
    val userService: API
        get() = retrofit.create(API::class.java)
}