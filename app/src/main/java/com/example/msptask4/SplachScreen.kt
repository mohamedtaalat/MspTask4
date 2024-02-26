package com.example.msptask4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class SplachScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)
         android.os.Handler().postDelayed({


          val intent=Intent(this,LoginActivity::class.java)
          startActivity(intent)
          finish()
         },3000)


    }
}