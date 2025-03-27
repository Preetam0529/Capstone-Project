package com.example.capstoneproject.ui.user_info

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.R
import com.example.capstoneproject.DatabaseHelper
import com.example.capstoneproject.LoginActivity
import java.io.ByteArrayOutputStream
import java.io.InputStream

class NameActivity : AppCompatActivity() {

    private lateinit var imgProfile: ImageView
    private lateinit var btnSelectImage: Button
    private lateinit var etFullName: EditText
    private lateinit var btnSubmit: Button
    private val PICK_IMAGE_REQUEST = 1
    private var imageUri: Uri? = null
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)

        imgProfile = findViewById(R.id.imgProfile)
        btnSelectImage = findViewById(R.id.btnSelectImage)
        etFullName = findViewById(R.id.etFullName)
        btnSubmit = findViewById(R.id.btnSubmit)

        // Initialize DatabaseHelper
        dbHelper = DatabaseHelper(this)

        // Select Image button click
        btnSelectImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        // Submit button click
        btnSubmit.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            if (fullName.isEmpty()) {
                Toast.makeText(this, "Please enter your full name", Toast.LENGTH_SHORT).show()
            } else {
                val success = dbHelper.insertProfileData(fullName, imageUri?.let { uriToByteArray(it) })
                if (success) {
                    Toast.makeText(this, "Profile saved successfully!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, AgeSelectActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Failed to save profile", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Handle the image result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.data
            imgProfile.setImageURI(imageUri)
        }
    }

    // Convert image URI to byte array
    private fun uriToByteArray(uri: Uri): ByteArray? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val byteBuffer = ByteArrayOutputStream()
            val buffer = ByteArray(1024)
            var length: Int
            while (inputStream?.read(buffer).also { length = it ?: -1 } != -1) {
                byteBuffer.write(buffer, 0, length)
            }
            byteBuffer.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
