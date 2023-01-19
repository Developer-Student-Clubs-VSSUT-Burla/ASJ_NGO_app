package com.example.asjapp.retrofit

class ResponseOwnerDetail : ArrayList<ResponseOwnerDetailItem>()


data class ResponseOwnerDetailItem(
    val _id: Int,
    val email: String,
    val name: String,
    val username: String
)