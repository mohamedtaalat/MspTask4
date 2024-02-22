package com.example.msptask4.Data

class UserRepo( private val dao: Dao) {
fun addUser(user: User){
    dao.addUser(user)

}
    fun GetId(phonenumber:Int):Int{
       return dao.GetId(phonenumber)
    }
    fun GetPassword(phonenumber:Int):String{
        return dao.GetPassword(phonenumber)
    }
    fun GetPhoneNumber(phonenumber: Int):Int{
        return dao.GetPhoneNumber(phonenumber)
    }
    fun GetUserName(phonenumber: Int):String{
        return dao.GetUserName(phonenumber)
    }
    fun GetAllUser():List<User>{
        return dao.SelectAllUser()
    }

}