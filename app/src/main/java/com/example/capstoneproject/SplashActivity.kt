package com.example.capstoneproject

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.capstoneproject.ui.user_info.FamilyHistoryActivity
import com.example.capstoneproject.ui.user_info.PreexistingConditionsActivity
import com.example.capstoneproject.ui.user_info.RecentIllnessActivity
import com.example.capstoneproject.ui.user_info.SmokeDrinkActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, PreexistingConditionsActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 2000)
    }
}
