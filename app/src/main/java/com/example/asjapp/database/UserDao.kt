package com.example.asjapp.database

import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addUser(user: UserEntity)

    @Query("SELECT * FROM Users")
    suspend fun getUser(): List<UserEntity>



    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("DELETE FROM Users WHERE token=:tokenId")
    suspend fun deleteByTokenId(tokenId: String)
}