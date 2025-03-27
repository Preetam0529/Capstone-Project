package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class WelcomeActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome3)

        val getStartedButton: Button = findViewById(R.id.btn_continue) // Ensure this ID exists in activity_welcome3.xml
        getStartedButton.setOnClickListener {
            val intent = Intent(this, WelcomeActivity2::class.java) // Replace with the main screen activity
            startActivity(intent)
            finish() // End welcome flow
        }
    }
}
