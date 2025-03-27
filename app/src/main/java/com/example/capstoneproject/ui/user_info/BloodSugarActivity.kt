package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.*

class BloodSugarActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_sugar)

        databaseHelper = DatabaseHelper(this)

        val bloodSugarInput = findViewById<EditText>(R.id.bloodsugarInput)
        val submitButton = findViewById<Button>(R.id.submitbloodSugar)

        submitButton.setOnClickListener {
            val bloodSugar = bloodSugarInput.text.toString().trim()

            if (bloodSugar.isEmpty()) {
                Toast.makeText(this, "Please enter your blood sugar level!", Toast.LENGTH_SHORT).show()
            } else {
                val values = ContentValues().apply {
                    put("bs", bloodSugar)
                }

                val isSuccess = databaseHelper.insertOrUpdateData(values)
                if (isSuccess) {
                    Toast.makeText(this, "Blood sugar level saved successfully!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
