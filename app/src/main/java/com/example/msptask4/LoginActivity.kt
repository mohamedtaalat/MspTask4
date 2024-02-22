package com.example.msptask4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msptask4.Data.UserDatabase
import com.example.msptask4.Data.UserRepo
import com.example.msptask4.Recyclerview.Adapter

class LoginActivity : AppCompatActivity() {
    lateinit var SignupButton: Button
    lateinit var LoginButton:Button
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var phone:EditText
    lateinit var remeberme:CheckBox
    val database:UserDatabase by lazy{
        UserDatabase.getDatabase(baseContext)
    }
    lateinit var repo:UserRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        repo= UserRepo(database.dao())
        remeberme=findViewById(R.id.checkBox)
        SignupButton  =findViewById(R.id.btsignup)
        LoginButton=findViewById(R.id.btlogin)
        email=findViewById(R.id.edName)
        password=findViewById(R.id.edPassword)
        phone=findViewById(R.id.edPhone)
        var sharedPref=getSharedPreferences("mohamed", MODE_PRIVATE)
        val editor=sharedPref.edit()
        var homes=repo.GetAllUser()
        var adapter= Adapter(homes)




        SignupButton.setOnClickListener{
            val intent=Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }
        LoginButton.setOnClickListener{
            if (repo.GetPassword(phone.text.toString().toInt()).toString()==password.text.toString())
            {
                if (repo.GetPhoneNumber(phone.text.toString().toInt()).toString()==phone.text.toString())
                {
                    if(repo.GetUserName(phone.text.toString().toInt()).toString()==email.text.toString()) {
                        Toast.makeText(this, "succesful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, Home::class.java)
                        startActivity(intent)

                    }
                }
            }
        }
        remeberme.setOnClickListener{
            val email1=email.text.toString()
            val password1=password.text.toString()
            val phone1=phone.text.toString().toInt()

            editor.apply {
                putString("email",email1)
                putString("password",password1)
                putInt("phone",phone1)
                commit()
            }
            val email2=sharedPref.getString("email",null)
            val password2=sharedPref.getString("password",null)
            val phone2 =sharedPref.getInt("phone",0)
            email.setText(email1)
            password.setText(password2)
            phone.setText(phone2.toString())
        }
        val myPutextra=getIntent().getIntExtra("mohamed",0)
        if (myPutextra==11){
            sharedPref=null
        }

          if(sharedPref!=null){
              val intent=Intent(this,Home::class.java)
              startActivity(intent)
          }


    }
}