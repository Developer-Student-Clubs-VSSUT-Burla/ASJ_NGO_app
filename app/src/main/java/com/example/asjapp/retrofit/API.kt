package com.example.asjapp.retrofit

import retrofit2.http.Body
import retrofit2.http.POST

interface API {

<<<<<<< HEAD
    @POST("api/users/login/")
    fun loginUser(@Body userLogin: UserLogin): retrofit2.Call<LoginResponse>

    @POST("api/users/register/")
=======
    @POST("api/login/")
    fun loginUser(@Body userLogin: UserLogin): retrofit2.Call<LoginResponse>

    @POST("api/register/")
>>>>>>> 3f1e1792612391dac10ef5f67bf6df23284cb42c
    fun registerUser(@Body user: UserRegister): retrofit2.Call<SignupResponse>
}
