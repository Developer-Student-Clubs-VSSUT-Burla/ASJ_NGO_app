package com.example.asjapp.retrofit

import com.google.gson.annotations.SerializedName


data class NgoDonated(
    @SerializedName("_id")
    val _id: Int,
    val contact: String,
    val desc: String,
    val location: String,
    val name: String,
    val ngo_owner: String,
    val startedAt: String,
    val tagline: String
)