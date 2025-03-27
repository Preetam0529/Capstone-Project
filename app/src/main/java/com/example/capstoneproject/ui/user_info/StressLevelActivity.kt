package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.R
import com.example.capstoneproject.ui.profile.ProfileActivity

class StressLevelActivity : AppCompatActivity() {

    private lateinit var radioGroupStress: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stress_level)

        // Initialize views
        radioGroupStress = findViewById(R.id.bpStatusOptions) // ID from XML
        submitButton = findViewById(R.id.submitBP) // Submit button
        databaseHelper = DatabaseHelper(this)

        submitButton.setOnClickListener {
            saveUserSelection()
        }
    }

    private fun saveUserSelection() {
        val selectedId = radioGroupStress.checkedRadioButtonId

        if (selectedId == -1) {
            Toast.makeText(this, "Please select your stress level!", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedStressLevel = findViewById<RadioButton>(selectedId).text.toString()

        val values = ContentValues().apply {
            put("stress_level", selectedStressLevel)
        }

        val isSuccess = databaseHelper.insertOrUpdateData(values)

        if (isSuccess) {
            Toast.makeText(this, "Stress Level saved successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, SmokeDrinkActivity::class.java)) // Navigate to ProfileActivity
            finish()
        } else {
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
        }
    }
}
