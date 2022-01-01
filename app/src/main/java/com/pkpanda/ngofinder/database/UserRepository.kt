package com.pkpanda.ngofinder.database

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val getUser:LiveData<UserEntity> = userDao.getUser()
    suspend fun addUser(user:UserEntity)
    {
        userDao.registerUser(user)
    }
}