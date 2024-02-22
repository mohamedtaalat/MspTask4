package com.example.msptask4.Data

import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
@Entity(indices = [Index(value = ["PhoneNumber"],
    unique = true)],tableName = "User")
data class User (
     var email:String,
     @ColumnInfo(name = "PhoneNumber")
     var phoneNumber:Int,
     var password:String,

     @PrimaryKey(autoGenerate = true)
     var id :Int
 )