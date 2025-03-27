package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R

class CholesterolActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cholesterol)

        dbHelper = DatabaseHelper(this)

        val submitButton: Button = findViewById(R.id.submitBP)
        val bpStatusOptions: RadioGroup = findViewById(R.id.bpStatusOptions)

        submitButton.setOnClickListener {
            val selectedId = bpStatusOptions.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "Please select a cholesterol status", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val selectedButton: RadioButton = findViewById(selectedId)
            val cholesterolStatus = selectedButton.text.toString()

            val values = ContentValues().apply {
                put("cholesterol", cholesterolStatus)
            }

            if (dbHelper.insertOrUpdateData(values)) {
                Toast.makeText(this, "Cholesterol Level Saved", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
