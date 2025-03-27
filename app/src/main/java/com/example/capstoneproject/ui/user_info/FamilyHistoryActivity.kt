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

class FamilyHistoryActivity : AppCompatActivity() {

    private lateinit var radioGroupFamilyHistory: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_family_history)

        radioGroupFamilyHistory = findViewById(R.id.radioGroupFamilyHistory)
        submitButton = findViewById(R.id.fhsubmit)

        dbHelper = DatabaseHelper(this)

        submitButton.setOnClickListener {
            saveFamilyHistory()
        }
    }

    private fun saveFamilyHistory() {
        val selectedId = radioGroupFamilyHistory.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Please select an option for family history!", Toast.LENGTH_SHORT).show()
            return
        }
        val familyHistoryChoice = findViewById<RadioButton>(selectedId).text.toString()
        val values = ContentValues().apply {
            put("family_history", familyHistoryChoice)
        }
        val isSuccess = dbHelper.insertOrUpdateData(values)
        if (isSuccess) {
            Toast.makeText(this, "Family history saved successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, RecentIllnessActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
        }
    }
}
