package com.rajpurohit.neostoreapp.rmdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User_info")
data class User(val fname:String,val lname:String,val email:String,val password:String,val phoneon:Int,val gender:String) {
    @PrimaryKey(autoGenerate = true) var id = 1
}
