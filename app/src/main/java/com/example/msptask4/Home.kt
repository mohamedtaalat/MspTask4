package com.example.msptask4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
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

        inzlitaion()

        repo= UserRepo(database.dao())
        var homes=repo.GetAllUser()
        var adapter= Adapter(homes)
        Recyclerview.adapter=adapter
        Recyclerview.layoutManager=LinearLayoutManager(this)




        AddBtn.setOnClickListener{
           addButoon()
        }
        LogOutBtn.setOnClickListener{

        logout()
        }

        adapter.setOnItemClickListener(object :Adapter.onItemCliclListener{
            override fun onItemClick(position: Int) {

               var intent=Intent(baseContext,ItemRecyclerView::class.java)
                intent.putExtra("mohamed",homes.get(position).phoneNumber)
                startActivity(intent)
                
            }


        })



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
    fun inzlitaion(){
        LogOutBtn=findViewById(R.id.btLogout)
        AddBtn=findViewById(R.id.btAdd)
        Recyclerview=findViewById(R.id.rec)
    }
    fun logout(){
        var intent=Intent(this,LoginActivity::class.java)
        intent.putExtra("mohamed",11)
        startActivity(intent)
    }
    fun addButoon(){
        var intent=Intent(this,SignupActivity::class.java)
        intent.putExtra("mohamed",11)
        startActivity(intent)
    }


}