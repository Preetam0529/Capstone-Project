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

class BloodPressureActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_pressure)

        databaseHelper = DatabaseHelper(this)

        val bpStatusOptions = findViewById<RadioGroup>(R.id.bpStatusOptions)
        val submitButton = findViewById<Button>(R.id.submitBP)

        submitButton.setOnClickListener {
            val selectedId = bpStatusOptions.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please select your BP status!", Toast.LENGTH_SHORT).show()
            } else {
                val selectedBP = findViewById<RadioButton>(selectedId).text.toString()

                val values = ContentValues().apply {
                    put("bp", selectedBP)
                }

                val isSuccess = databaseHelper.insertOrUpdateData(values)
                if (isSuccess) {
                    Toast.makeText(this, "Blood Pressure status saved successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, com.example.capstoneproject.MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
