package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R

class GenderSelectActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender_select)

        // Initialize Database Helper
        databaseHelper = DatabaseHelper(this)

        // Initialize UI Elements
        val maleButton = findViewById<ImageButton>(R.id.maleButton)
        val femaleButton = findViewById<ImageButton>(R.id.femaleButton)

        // Click listeners for gender selection
        maleButton.setOnClickListener {
            saveGender("Male")
        }

        femaleButton.setOnClickListener {
            saveGender("Female")
        }
    }

    private fun saveGender(gender: String) {
        val values = ContentValues().apply {
            put("gender", gender)
        }

        val isSuccess = databaseHelper.insertOrUpdateData(values)
        if (isSuccess) {
            Toast.makeText(this, "$gender selected successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, BmiActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Failed to save gender", Toast.LENGTH_SHORT).show()
        }
    }
}
