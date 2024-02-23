package com.example.msptask4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.msptask4.Data.UserDatabase
import com.example.msptask4.Data.UserRepo
import com.example.msptask4.Recyclerview.Adapter

class Home : AppCompatActivity() {
    lateinit var LogOutBtn:Button
    lateinit var AddBtn:Button
    lateinit var Recyclerview:RecyclerView
    lateinit var DeleteButton:Button

    val database: UserDatabase by lazy{
        UserDatabase.getDatabase(baseContext)
    }
    lateinit var repo: UserRepo
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        repo= UserRepo(database.dao())
        LogOutBtn=findViewById(R.id.btLogout)
        AddBtn=findViewById(R.id.btAdd)
        Recyclerview=findViewById(R.id.rec)



        LogOutBtn.setOnClickListener{
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)


        }
        var homes=repo.GetAllUser()
        var adapter= Adapter(homes)
        Recyclerview.adapter=adapter
        Recyclerview.layoutManager=LinearLayoutManager(this)
        AddBtn.setOnClickListener{
            var intent=Intent(this,SignupActivity::class.java)
            intent.putExtra("mohamed",11)
            startActivity(intent)
        }
        LogOutBtn.setOnClickListener{
            var intent=Intent(this,LoginActivity::class.java)
            intent.putExtra("mohamed",11)
            startActivity(intent)

        }

        adapter.setOnItemClickListener(object :Adapter.onItemCliclListener{
            override fun onItemClick(position: Int) {

               var intent=Intent(baseContext,ItemRecyclerView::class.java)
                intent.putExtra("mohamed",homes.get(position).phoneNumber)
                startActivity(intent)

            }


        })



    }
}