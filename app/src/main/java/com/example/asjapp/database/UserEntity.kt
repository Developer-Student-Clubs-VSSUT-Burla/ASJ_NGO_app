package com.example.asjapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "Name")
    val name: String,
    @ColumnInfo(name = "Email")
    val email: String,
    @ColumnInfo(name = "Bio")
    val bio: String,
    @ColumnInfo(name = "token")
    val token: String
)