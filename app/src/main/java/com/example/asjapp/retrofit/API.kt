package com.example.asjapp.retrofit

import android.util.Log
import com.example.asjapp.MainActivity
import com.example.asjapp.database.SessionManager
import com.example.asjapp.database.UserDatabase
import com.example.asjapp.database.UserEntity
import com.example.asjapp.login.token
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.*


private lateinit var sessionManager: SessionManager

interface API {

    @POST("api/users/login/")
    fun loginUser(@Body userLogin: UserLogin): retrofit2.Call<LoginResponse>

    @POST("api/users/register/")
    fun registerUser(@Body user: UserRegister): retrofit2.Call<SignupResponse>


    @GET("/api/ngos/")
    suspend fun getNgos(): Response<List<Ngo>>

    @GET("api/users/profile")
    suspend fun getProfile(@Header("Authorization") token: String): Response<User>

    @GET("api/users/donors/")
    suspend fun getOwnNgos(): Response<List<SubscribedNgo>>

    @POST("api/ngos/add/")
    fun postNgo(@Body requestClass: RequestClass): retrofit2.Call<ResponseNgo>

    @POST("api/ngoOwners/register/")
    fun postowner(@Body requestOwner: RequestOwner): retrofit2.Call<ResponseOwner>

    @PUT("/api/users/profile/joinNgo/")
    fun postJoinedNgo(
        @Query("ngo") ngo: Int,
        @Header("Authorization") token: String
    ): retrofit2.Call<User>

}
