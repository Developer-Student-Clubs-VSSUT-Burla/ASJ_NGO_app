package com.example.asjapp.retrofit

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var name: String,
    var email: String,
    var token: String,
    var _id: Int
)
