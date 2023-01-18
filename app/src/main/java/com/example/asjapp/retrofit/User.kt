package com.example.asjapp.retrofit

data class User(
    val _id: Int,
    val address: String,
    val city: Any,
    val date_joined: String,
    val dob: Any,
    val email: String,
    val father_s_name: Any,
    val first_name: String,
    val id: Int,
    val isAdmin: Boolean,
    val last_name: String,
    val mother_s_name: Any,
    val name: String,
    val pincode: Any,
    val state: Any,
    val subscribedNgos: List<SubscribedNgoX>,
    val username: String
)