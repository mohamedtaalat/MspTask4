package com.example.msptask4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.msptask4.Data.UserDatabase
import com.example.msptask4.Data.UserRepo
import com.example.msptask4.Recyclerview.Adapter

class ItemRecyclerView : AppCompatActivity() {
    lateinit var textEmail:TextView
    lateinit var TextPassword:TextView
    lateinit var TextPhone:TextView
    lateinit var TextId:TextView
    lateinit var DeleteButton:Button
    val database: UserDatabase by lazy{
        UserDatabase.getDatabase(baseContext)
    }
    lateinit var repo: UserRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_recycler_view)
        DeleteButton=findViewById(R.id.DeleteButton)

       var myPutExtra=getIntent().getIntExtra("mohamed",0)
        var repo=UserRepo(database.dao())
        textEmail=findViewById(R.id.TextEmailItem)
        TextPassword=findViewById(R.id.TextPasswordItem)
        TextId=findViewById(R.id.TextIdItem)
        TextPhone=findViewById(R.id.TextPhoneItem)
        textEmail.text=repo.GetUserName(myPutExtra).toString()
        TextPassword.text=repo.GetPassword(myPutExtra)
        TextId.text=repo.GetId(myPutExtra).toString()
        TextPhone.text=myPutExtra.toString()

        DeleteButton.setOnClickListener{
            repo.DeleteUser(repo.GetOneUser(TextId.text.toString().toInt()))
            Toast.makeText(this, "Deleting", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,Home::class.java)
            startActivity(intent)
        }

    }
}