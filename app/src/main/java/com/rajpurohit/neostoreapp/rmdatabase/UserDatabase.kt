package com.rajpurohit.neostoreapp.rmdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(User::class),version =1,exportSchema = false)
abstract class UserDataBase: RoomDatabase() {
    abstract fun getUserDao() : UserDao
    companion object{
        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getDatabase(context: Context): UserDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBase::class.java,
                    "User_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }
}