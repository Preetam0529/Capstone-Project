package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        findViewById<TextView>(R.id.registerHere).setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}
