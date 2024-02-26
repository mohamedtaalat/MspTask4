package com.example.msptask4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.actions.ModeAction
import android.util.Log
import android.view.Display.Mode
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.msptask4.Data.UserDatabase
import com.example.msptask4.Data.UserRepo
import com.example.msptask4.Recyclerview.Adapter
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.Snackbar.make
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    lateinit var SignupButton: Button
    lateinit var LoginButton:Button
    lateinit var email:TextInputEditText
    lateinit var password:TextInputEditText
    lateinit var phone:TextInputEditText
    lateinit var remeberme:CheckBox
    lateinit var snackbar: Snackbar

    val database:UserDatabase by lazy{
        UserDatabase.getDatabase(baseContext)
    }
    lateinit var repo:UserRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        repo= UserRepo(database.dao())
        var homes=repo.GetAllUser()
        var adapter= Adapter(homes)
        intlization()
        val TAG="mainActivity"

        var sharedPref=getSharedPreferences("mohamed", MODE_PRIVATE)
        var editor=sharedPref.edit()
        var emailPutShared:String?
        var passwordPutShared:String?
        var phonePutShared:Int
        remeberme.setOnClickListener{
             emailPutShared=email.text.toString()
             passwordPutShared= password.text.toString()
             phonePutShared=phone.text.toString().toInt()

            editor.apply {
                putString("email",emailPutShared)
                putString("password",passwordPutShared)
                putInt("phone",phonePutShared)
                commit()
            }



        }

        var emailSetShared=sharedPref.getString("email",null)
        var passwordSetShared=sharedPref.getString("password",null)
        var phoneSetShared =sharedPref.getInt("phone",0)


        SignupButton.setOnClickListener{
            SignUpButton()

        }
        LoginButton.setOnClickListener{
            loginButton()
        }




        var MyPutText=getIntent().getIntExtra("mohamed",0)
        if (MyPutText==11){

            emailSetShared=null
            passwordSetShared=null
            phoneSetShared=0
            email.text=null
            password.text=null
            phone.text=null
            LoginButton.setOnClickListener{
                loginButton()
            }
            Log.d(TAG, "onCreate: if")


        }
        else{
            email.setText(emailSetShared)
            password.setText(passwordSetShared)
            phone.setText(phoneSetShared.toString())
            if (emailSetShared==repo.GetUserName(phoneSetShared)){
                if (passwordSetShared==repo.GetPassword(phoneSetShared)){
                    if (phoneSetShared==repo.GetPhoneNumber(phoneSetShared)){
                        val intent=Intent(this,Home::class.java)
                        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                        Log.d(TAG, "onCreate:else ")

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
    fun intlization(){

        remeberme=findViewById(R.id.checkBox)
        SignupButton  =findViewById(R.id.btsignup)
        LoginButton=findViewById(R.id.btlogin)
        email=findViewById(R.id.textemail)
        password=findViewById(R.id.textPassword)
        phone=findViewById(R.id.textPhone)



    }
    fun loginButton(){
        if (repo.GetPhoneNumber(phone.text.toString().toInt()).toString()==phone.text.toString())
        {

            if (repo.GetPassword(phone.text.toString().toInt()).toString()==password.text.toString()) {

                if (repo.GetUserName(phone.text.toString().toInt()).toString() == email.text.toString()) {
                    Toast.makeText(this, "succesful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)

                }else{
                    Snackbar.make(phone,"email not correct",Snackbar.LENGTH_SHORT).show()
                    email.setError("email wrong")
                }
            }else{

                Snackbar.make(phone,"password not correct",Snackbar.LENGTH_SHORT).show()
                password.setError("Password wrong")
            }
        }
        else{
            Snackbar.make(phone,"phoneNumber not correct",Snackbar.LENGTH_SHORT).show()
            phone.setError("phone wrong")

        }
    }
    fun SignUpButton(){
        val intent=Intent(this,SignupActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.Share){
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
        }
        else if (item.itemId==R.id.Settings){
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
        }else if (item.itemId==R.id.Contact){
            Toast.makeText(this, "Contact us", Toast.LENGTH_SHORT).show()
        }
        return true
    }


}