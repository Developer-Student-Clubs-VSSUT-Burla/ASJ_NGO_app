package com.pkpanda.ngofinder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun registerUser(userEntity: UserEntity?)

    @Query("SELECT * FROM Users WHERE id=1")
    fun getUser():LiveData<UserEntity>
}