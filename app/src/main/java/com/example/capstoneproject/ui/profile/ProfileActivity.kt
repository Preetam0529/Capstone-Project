package com.example.capstoneproject.ui.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var username: String // Fetch from SharedPreferences or login session

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbHelper = DatabaseHelper(this)

        val userData = dbHelper.getUserData(username)

        if (userData != null) {
            // Retrieve and set profile image
            val profileImageView = findViewById<ImageView>(R.id.ivProfilePic)
            val imageBytes = dbHelper.getProfileImage(username)
            if (imageBytes != null) {
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                profileImageView.setImageBitmap(bitmap)
            } else {
                profileImageView.setImageResource(R.drawable.default_background_image)
            }

            // Personal Information
            findViewById<TextView>(R.id.tvUsername).text = "Username: ${userData.getAsString("username")}"
            findViewById<TextView>(R.id.tvName).text = "Full Name: ${userData.getAsString("fullname")}"
            findViewById<TextView>(R.id.tvEmail).text = "Email: ${userData.getAsString("email")}"
            findViewById<TextView>(R.id.tvAge).text = "Age: ${userData.getAsString("age")}"
            findViewById<TextView>(R.id.tvGender).text = "Gender: ${userData.getAsString("gender")}"
            findViewById<TextView>(R.id.tvDietType).text = "Diet Type: ${userData.getAsString("diet_type")}"

            // Health Data
            findViewById<TextView>(R.id.tvHeight).text = "Height: ${userData.getAsString("height")}"
            findViewById<TextView>(R.id.tvWeight).text = "Weight: ${userData.getAsString("weight")}"
            findViewById<TextView>(R.id.tvBmi).text = "BMI: ${userData.getAsString("bmi")}"
            findViewById<TextView>(R.id.tvBP).text = "Blood Pressure: ${userData.getAsString("bp")}"
            findViewById<TextView>(R.id.tvHR).text = "Heart Rate: ${userData.getAsString("hr")}"
            findViewById<TextView>(R.id.tvBS).text = "Blood Sugar: ${userData.getAsString("bs")}"
            findViewById<TextView>(R.id.tvOS).text = "Oxygen Saturation: ${userData.getAsString("os")}"
            findViewById<TextView>(R.id.tvPA).text = "Physical Activity: ${userData.getAsString("pa")}"
            findViewById<TextView>(R.id.tvSH).text = "Sleep Hours: ${userData.getAsString("sh")}"
            findViewById<TextView>(R.id.tvBT).text = "Body Temperature: ${userData.getAsString("bt")}"
            findViewById<TextView>(R.id.tvCH).text = "Cholesterol: ${userData.getAsString("ch")}"
            findViewById<TextView>(R.id.tvStress).text = "Stress Level: ${userData.getAsString("stress")}"
            findViewById<TextView>(R.id.tvSmokeDrink).text = "Smoke/Drink: ${userData.getAsString("smoke")}, ${userData.getAsString("drink")}"
            findViewById<TextView>(R.id.tvPreExistingConditions).text = "Preexisting Conditions: ${userData.getAsString("preexisting_conditions")}"
            findViewById<TextView>(R.id.tvFamilyHistory).text = "Family History: ${userData.getAsString("family_history")}"
            findViewById<TextView>(R.id.tvRecentIllness).text = "Recent Illness: ${userData.getAsString("recent_illness")}"
        } else {
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
        }
    }
}
