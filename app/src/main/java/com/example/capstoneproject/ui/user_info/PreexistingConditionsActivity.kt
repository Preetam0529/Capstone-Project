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

class PreexistingConditionsActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preexisting_conditions)

        // Initialize Database Helper
        dbHelper = DatabaseHelper(this)

        // Initialize UI Elements
        val conditionsGroup = findViewById<RadioGroup>(R.id.radioGroupConditions)
        val submitButton = findViewById<Button>(R.id.submit_button)

        submitButton.setOnClickListener {
            val selectedId = conditionsGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Please select a condition!", Toast.LENGTH_SHORT).show()
            } else {
                val selectedCondition = findViewById<RadioButton>(selectedId).text.toString()
                saveCondition(selectedCondition)
            }
        }
    }

    private fun saveCondition(condition: String) {
        val values = ContentValues().apply {
            put("preexisting_condition", condition)
        }

        val isSuccess = dbHelper.insertOrUpdateData(values)
        if (isSuccess) {
            Toast.makeText(this, "Condition saved successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, FamilyHistoryActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Failed to save condition", Toast.LENGTH_SHORT).show()
        }
    }
}
