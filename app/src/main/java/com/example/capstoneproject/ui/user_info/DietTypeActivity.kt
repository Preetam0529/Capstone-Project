package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R
import com.google.android.material.button.MaterialButton

class DietTypeActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet_type)

        databaseHelper = DatabaseHelper(this)

        val buttonVeg = findViewById<MaterialButton>(R.id.button_veg)
        val buttonNonVeg = findViewById<MaterialButton>(R.id.button_non_veg)
        val buttonVegan = findViewById<MaterialButton>(R.id.button_vegan)
        val buttonKeto = findViewById<MaterialButton>(R.id.button_keto)

        buttonVeg.setOnClickListener { saveDietType("Veg") }
        buttonNonVeg.setOnClickListener { saveDietType("Non-Veg") }
        buttonVegan.setOnClickListener { saveDietType("Vegan") }
        buttonKeto.setOnClickListener { saveDietType("Keto") }
    }

    private fun saveDietType(dietType: String) {
        val values = ContentValues().apply {
            put("diet_type", dietType)
        }

        val isSuccess = databaseHelper.insertOrUpdateData(values)
        if (isSuccess) {
            Toast.makeText(this, "Diet type saved successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, StressLevelActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Failed to save diet type", Toast.LENGTH_SHORT).show()
        }
    }
}
