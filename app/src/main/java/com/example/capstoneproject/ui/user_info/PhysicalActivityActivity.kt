package com.example.capstoneproject.ui.user_info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R


class PhysicalActivityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_physical_activity)

        val radioGroup = findViewById<RadioGroup>(R.id.bpStatusOptions)
        val submitButton = findViewById<Button>(R.id.submitBP)

        submitButton.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(this, "Please select your activity level!", Toast.LENGTH_SHORT).show()
            } else {
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val activityLevel = selectedRadioButton.text.toString()

                val dbHelper = DatabaseHelper(this)
                val isInserted = dbHelper.insertUserData("physical_activity", activityLevel)

                if (isInserted) {
                    Toast.makeText(this, "Physical activity level saved!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

