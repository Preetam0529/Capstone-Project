package com.example.capstoneproject.ui.user_info

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R


class SleepHoursActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_hours)

        val sleepHourInput = findViewById<EditText>(R.id.sleephourInput)
        val submitButton = findViewById<Button>(R.id.submitsleepHour)

        submitButton.setOnClickListener {
            val sleepHours = sleepHourInput.text.toString()
            if (sleepHours.isEmpty()) {
                Toast.makeText(this, "Please enter your sleep hours!", Toast.LENGTH_SHORT).show()
            } else {
                val dbHelper = DatabaseHelper(this)
                val isInserted = dbHelper.insertUserData("sleep_hours", sleepHours)

                if (isInserted) {
                    Toast.makeText(this, "Sleep hours saved!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
