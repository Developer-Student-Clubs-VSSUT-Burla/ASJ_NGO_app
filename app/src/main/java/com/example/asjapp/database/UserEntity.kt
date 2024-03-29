package com.example.asjapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "bio")
    val bio: String,
    @ColumnInfo(name = "token")
    val token: String,
    @ColumnInfo(name = "uId")
    val uId: String
)