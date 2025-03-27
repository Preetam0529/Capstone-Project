package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class WelcomeActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome2)

        val continueButton: Button = findViewById(R.id.btnContinue) // Ensure this ID exists in activity_welcome2.xml
        continueButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity1::class.java)
            startActivity(intent)
        }
    }
}

