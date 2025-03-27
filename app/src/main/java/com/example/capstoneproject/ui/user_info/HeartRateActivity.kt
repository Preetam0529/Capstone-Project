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


class HeartRateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_rate)

        val heartRateInput = findViewById<EditText>(R.id.heartRateInput)
        val submitButton = findViewById<Button>(R.id.submitHeartRate)

        submitButton.setOnClickListener {
            val heartRate = heartRateInput.text.toString().trim()

            if (heartRate.isEmpty()) {
                Toast.makeText(this, "Please enter your heart rate!", Toast.LENGTH_SHORT).show()
            } else {
                saveHeartRateToDatabase(heartRate)
                Toast.makeText(this, "Heart rate saved: $heartRate BPM", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun saveHeartRateToDatabase(heartRate: String) {
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.writableDatabase

        val query = "INSERT INTO UserHealthData (heartRate) VALUES (?)"
        val statement = db.compileStatement(query)
        statement.bindString(1, heartRate)
        statement.executeInsert()

        db.close()
    }
}
