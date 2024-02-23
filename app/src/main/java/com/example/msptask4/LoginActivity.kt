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


        var homes=repo.GetAllUser()
        var adapter= Adapter(homes)
        var sharedPref=getSharedPreferences("mohamed", MODE_PRIVATE)
        val editor=sharedPref.edit()



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

                    }else{
                        Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        remeberme.setOnClickListener{
            val emailPutShared=email.text.toString()
            val passwordPutShared=password.text.toString()
            val phonePutShared=phone.text.toString().toInt()

            editor.apply {
                putString("email",emailPutShared)
                putString("password",passwordPutShared)
                putInt("phone",phonePutShared)
                commit()
            }



        }

        val emailSetShared=sharedPref.getString("email",null)
        val passwordSetShared=sharedPref.getString("password",null)
        val phoneSetShared =sharedPref.getInt("phone",0)
        email.setText(emailSetShared)
        password.setText(passwordSetShared)
        phone.setText(phoneSetShared.toString())

        var MyPutText=getIntent().getIntExtra("mohamed",0)
        if (MyPutText==11){
            sharedPref=null
            email.text=null
            password.text=null
            phone.text=null
        }
        else{
            if (emailSetShared==repo.GetUserName(phoneSetShared)){
                if (passwordSetShared==repo.GetPassword(phoneSetShared)){
                    if (phoneSetShared==repo.GetPhoneNumber(phoneSetShared)){
                        val intent=Intent(this,Home::class.java)
                        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
                        startActivity(intent)

                    }
                    else{
                        Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
                    }

                }
                else{
                    Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
            }

        }





    }
}