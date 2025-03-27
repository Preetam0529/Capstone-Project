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
import com.example.capstoneproject.LoginActivity
import com.example.capstoneproject.R

class SmokeDrinkActivity : AppCompatActivity() {

    private lateinit var radioGroupSmoke: RadioGroup
    private lateinit var radioGroupDrink: RadioGroup
    private lateinit var submitButton: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_smoke_drink)

        radioGroupSmoke = findViewById(R.id.radioGroupSmoke)
        radioGroupDrink = findViewById(R.id.radioGroupDrink)
        submitButton = findViewById(R.id.submit_button) // Ensure there's a submit button in XML

        dbHelper = DatabaseHelper(this)

        submitButton.setOnClickListener {
            saveUserSelections()
        }
    }

    private fun saveUserSelections() {
        val selectedSmokeId = radioGroupSmoke.checkedRadioButtonId
        val selectedDrinkId = radioGroupDrink.checkedRadioButtonId

        if (selectedSmokeId == -1 || selectedDrinkId == -1) {
            Toast.makeText(this, "Please select an option for both smoking and drinking", Toast.LENGTH_SHORT).show()
            return
        }

        val smokeChoice = findViewById<RadioButton>(selectedSmokeId).text.toString()
        val drinkChoice = findViewById<RadioButton>(selectedDrinkId).text.toString()

        val values = ContentValues().apply {
            put("smoke", smokeChoice)
            put("drink", drinkChoice)
        }

        val isSuccess = dbHelper.insertOrUpdateData(values)

        if (isSuccess) {
            Toast.makeText(this, "Preferences saved successfully!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, PreexistingConditionsActivity::class.java))
            finish()
        } else {
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show()
        }
    }
}
