package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R

class AgeSelectActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var ageSpinner: Spinner
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_age_select)

        // Initialize Database Helper
        databaseHelper = DatabaseHelper(this)

        // Initialize UI elements
        ageSpinner = findViewById(R.id.ageSpinner)
        submitButton = findViewById(R.id.submitButton)

        // Populate Spinner with age options (1 to 100)
        val ageList = (1..100).map { it.toString() }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ageList)
        ageSpinner.adapter = adapter

        // Handle Submit Button Click
        submitButton.setOnClickListener {
            val selectedAge = ageSpinner.selectedItem.toString()

            val values = ContentValues().apply {
                put("age", selectedAge.toInt()) // Convert age to integer before storing
            }

            val isSuccess = databaseHelper.insertOrUpdateData(values)
            if (isSuccess) {
                Toast.makeText(this, "Age saved successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, GenderSelectActivity::class.java)) // Navigate to MainActivity
                finish()
            } else {
                Toast.makeText(this, "Failed to save age", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
