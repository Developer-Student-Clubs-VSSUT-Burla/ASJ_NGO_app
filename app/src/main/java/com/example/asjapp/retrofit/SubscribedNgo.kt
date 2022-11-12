package com.example.asjapp.retrofit

data class SubscribedNgo(
    val _id: Int,
    val amount_donated: Int,
    val ngo_donated: List<NgoDonated>,
    val ngo_member: List<NgoMemberResponse>,
    val ngo_user: NgoUser
)