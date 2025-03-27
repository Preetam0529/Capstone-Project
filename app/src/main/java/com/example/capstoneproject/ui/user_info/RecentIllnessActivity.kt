package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.*

class RecentIllnessActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recent_illness)

        // Initialize Database Helper
        databaseHelper = DatabaseHelper(this)

        // Initialize UI Elements
        val illnessGroup = findViewById<RadioGroup>(R.id.radioGroupIllness)
        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val selectedId = illnessGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please select an illness!", Toast.LENGTH_SHORT).show()
            } else {
                val selectedIllness = findViewById<RadioButton>(selectedId).text.toString()
                saveCondition(selectedIllness)
            }
        }
    }

    private fun saveCondition(illness: String) {
        val values = ContentValues().apply {
            put("recent_illness", illness)
        }

        val isSuccess = databaseHelper.insertOrUpdateData(values)
        if (isSuccess) {
            Toast.makeText(this, "Condition saved successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Failed to save illness", Toast.LENGTH_SHORT).show()
        }
    }
}
