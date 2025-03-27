package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        dbHelper = DatabaseHelper(this)

        val usernameInput = findViewById<EditText>(R.id.Username)
        val passwordInput = findViewById<EditText>(R.id.pass)
        val loginButton = findViewById<Button>(R.id.buttonlogin)
        val registerLink = findViewById<TextView>(R.id.registerHere)

        // Handle login button click
        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
            } else {
                val isValid = dbHelper.validateUser(username, password)

                if (isValid) {
                    Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Handle registration link click
        registerLink.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity3::class.java))
        }
    }
}
