package com.example.capstoneproject

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.ui.user_info.NameActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar?.hide()

        dbHelper = DatabaseHelper(this)

        val usernameInput = findViewById<EditText>(R.id.regUsername)
        val emailInput = findViewById<EditText>(R.id.regEmailid)
        val passwordInput = findViewById<EditText>(R.id.regpass1)
        val confirmPasswordInput = findViewById<EditText>(R.id.regpass2)
        val registerButton = findViewById<Button>(R.id.regbutton)

        registerButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()
            val confirmPassword = confirmPasswordInput.text.toString().trim()

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            } else {
                val values = ContentValues().apply {
                    put("username", username)
                    put("email", email)
                    put("password", password)
                }

                val isInserted = dbHelper.insertOrUpdateData(values)

                if (isInserted) {
                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, NameActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Registration Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
