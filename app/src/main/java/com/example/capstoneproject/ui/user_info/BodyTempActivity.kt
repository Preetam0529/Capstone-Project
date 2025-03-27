package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.R

class BodyTempActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_body_temp)

        databaseHelper = DatabaseHelper(this)

        val bodyTemperatureInput = findViewById<EditText>(R.id.bodyTemperatureInput)
        val submitButton = findViewById<Button>(R.id.submitBodyTemperature)

        submitButton.setOnClickListener {
            val bodyTemp = bodyTemperatureInput.text.toString().trim()

            if (bodyTemp.isEmpty()) {
                Toast.makeText(this, "Please enter your body temperature!", Toast.LENGTH_SHORT).show()
            } else {
                val values = ContentValues().apply {
                    put("bt", bodyTemp)
                }

                val isSuccess = databaseHelper.insertOrUpdateData(values)
                if (isSuccess) {
                    Toast.makeText(this, "Body temperature saved successfully!", Toast.LENGTH_SHORT).show()
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
