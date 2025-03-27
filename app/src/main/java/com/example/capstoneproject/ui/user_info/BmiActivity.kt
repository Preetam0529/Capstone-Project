package com.example.capstoneproject.ui.user_info

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.MainActivity
import com.example.capstoneproject.R

class BmiActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var heightInput: EditText
    private lateinit var weightInput: EditText
    private lateinit var bmiResultText: TextView
    private lateinit var bmiResult: TextView
    private lateinit var calculateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        // Initialize Database Helper
        databaseHelper = DatabaseHelper(this)

        // Initialize UI Elements
        heightInput = findViewById(R.id.heightInput)
        weightInput = findViewById(R.id.weightInput)
        bmiResultText = findViewById(R.id.bmiResultText)
        bmiResult = findViewById(R.id.bmiResult)
        calculateButton = findViewById(R.id.calculateButton)

        // Click listener for the Calculate Button
        calculateButton.setOnClickListener {
            calculateAndSaveBMI()
        }
    }

    private fun calculateAndSaveBMI() {
        val heightStr = heightInput.text.toString()
        val weightStr = weightInput.text.toString()

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            Toast.makeText(this, "Please enter both height and weight!", Toast.LENGTH_SHORT).show()
            return
        }

        val height = heightStr.toFloatOrNull()
        val weight = weightStr.toFloatOrNull()

        if (height == null || weight == null || height <= 0 || weight <= 0) {
            Toast.makeText(this, "Invalid height or weight values!", Toast.LENGTH_SHORT).show()
            return
        }

        // Calculate BMI
        val bmi = weight / (height * height)

        // Display BMI Result
        bmiResult.text = String.format("%.2f", bmi)

        // Save BMI to Database
        val values = ContentValues().apply {
            put("bmi", bmi)
            put("height",height)
            put("weight",weight)
        }

        val isSuccess = databaseHelper.insertOrUpdateData(values)
        if (isSuccess) {
            Toast.makeText(this, "BMI saved successfully!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, DietTypeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Failed to save BMI", Toast.LENGTH_SHORT).show()
        }
    }
}
