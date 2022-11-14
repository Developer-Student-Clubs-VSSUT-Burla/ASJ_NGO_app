package com.example.asjapp.retrofit

data class SubscribedNgo(
    val _id: Int,
    var amount_donated: Int,
    var ngo_donated: List<NgoDonated>?,
    var ngo_member: List<NgoMemberResponse>?,
    var ngo_user: NgoUser
)