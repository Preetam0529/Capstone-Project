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


class OxygenSaturationActivity : AppCompatActivity() {

    private lateinit var oxygenLevelInput: EditText
    private lateinit var submitButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oxygen_saturation)

        oxygenLevelInput = findViewById(R.id.oxygenLevelInput)
        submitButton = findViewById(R.id.submitOxygenLevel)
        dbHelper = DatabaseHelper(this)

        submitButton.setOnClickListener {
            val oxygenLevel = oxygenLevelInput.text.toString().trim()

            if (oxygenLevel.isEmpty()) {
                Toast.makeText(this, "Please enter your oxygen level!", Toast.LENGTH_SHORT).show()
            } else {
                val oxygenValue = oxygenLevel.toIntOrNull()
                if (oxygenValue != null && oxygenValue in 70..100) {
                    val success = dbHelper.insertOxygenSaturation(oxygenValue)
                    if (success) {
                        Toast.makeText(this, "Oxygen level saved successfully!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Failed to save data.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Please enter a valid oxygen level (70-100%)", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
