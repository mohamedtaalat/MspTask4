package com.example.msptask4.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.selects.select
@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)
    @Query("select password from user where PhoneNumber =:phonenumber")
    fun GetPassword(phonenumber:Int):String
    @Query("select id from user where PhoneNumber =:phonenumber")
    fun GetId(phonenumber:Int):Int
    @Query("select phoneNumber from User where PhoneNumber =:phonenumber ")
    fun GetPhoneNumber(phonenumber: Int):Int
    @Query("Select email from User where PhoneNumber =:phonenumber")
    fun GetUserName(phonenumber: Int):String
    @Query("select*from User")
    fun SelectAllUser():List<User>
}