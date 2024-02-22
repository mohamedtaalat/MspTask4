package com.example.msptask4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msptask4.Data.User
import com.example.msptask4.Data.UserDatabase
import com.example.msptask4.Data.UserRepo
import com.example.msptask4.Recyclerview.Adapter

class SignupActivity : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var phone:EditText
    lateinit var signup:Button
    lateinit var reminderMe:CheckBox
    lateinit var imageView: ImageView

    val database: UserDatabase by lazy{
        UserDatabase.getDatabase(baseContext)
    }
    lateinit var repo:UserRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        repo= UserRepo(database.dao())
        email=findViewById(R.id.edemail2)
        password=findViewById(R.id.edPassword2)
        phone=findViewById(R.id.edPhone2)
        signup=findViewById(R.id.SignUpBt)
        reminderMe=findViewById(R.id.checkBox2)
        imageView=findViewById(R.id.imageView3)
        var sharedPref=getSharedPreferences("mohamed", MODE_PRIVATE)
        val editor=sharedPref.edit()

        var homes=repo.GetAllUser()
        var adapter= Adapter(homes)






        signup.setOnClickListener{
            if (repo.GetPhoneNumber(phone.text.toString().toInt())!= phone.text.toString().toInt())
            {
                Toast.makeText(this, "incorrect", Toast.LENGTH_SHORT).show()
                if (password.text.toString().length>=8) {
                    var user=User(email.text.toString(),phone.text.toString().toInt(),password.text.toString(),
                        0)
                    repo.addUser(user)
                    Toast.makeText(this, "succesful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)


                }
                else{
                    Toast.makeText(this, "password 8 digit", Toast.LENGTH_SHORT).show()
                }
            }
        }

        reminderMe.setOnClickListener{
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

            val myPutExtra=getIntent().getIntExtra("mohamed",0)
        if (myPutExtra==11){
            sharedPref=null
        }
        if (sharedPref!=null){
            val intent=Intent(this,Home::class.java)

        }
    }
}