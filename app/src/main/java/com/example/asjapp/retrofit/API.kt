package com.example.asjapp.retrofit

import retrofit2.http.Body
import retrofit2.http.POST

interface API {

    @POST("api/login/")
    fun loginUser(@Body userLogin: UserLogin): retrofit2.Call<LoginResponse>

    @POST("api/register/")
    fun registerUser(@Body user: UserRegister): retrofit2.Call<SignupResponse>
}
