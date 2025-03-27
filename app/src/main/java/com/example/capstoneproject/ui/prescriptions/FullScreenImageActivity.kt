package com.example.capstoneproject.ui.prescriptions

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.capstoneproject.R

class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_image)
        supportActionBar?.hide()

        val imageView = findViewById<ImageView>(R.id.fullScreenImageView)
        val imageUri = intent.getStringExtra("image_uri")

        if (imageUri != null) {
            Glide.with(this)
                .load(imageUri)
                .into(imageView)
        } else {
            finish() // If URI is missing, close the activity
        }

        // Close the full-screen view when the user taps the image
        imageView.setOnClickListener {
            finish()
        }
    }
}
