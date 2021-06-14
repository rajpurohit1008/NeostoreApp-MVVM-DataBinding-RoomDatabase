package com.rajpurohit.neostoreapp.rmdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

   /* @Delete
    suspend fun delete(user: User)

    @Update()
    suspend fun update(user1: User)*/

    @Query("Select * from user_info")
    fun getAllUser():User
}