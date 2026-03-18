package com.azores_jc

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Mainpage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the action bar
        supportActionBar?.hide()

        setContentView(R.layout.activity_mainpage)

        // Set colored app name
        setAppName()

        // Initialize views
        //val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnGetStarted = findViewById<Button>(R.id.btnGetStarted)
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navMenu = findViewById<LinearLayout>(R.id.navMenu)
        val navOrders = findViewById<LinearLayout>(R.id.navOrders)
        val navAccount = findViewById<LinearLayout>(R.id.navAccount)

        // Login button click
        //btnLogin.setOnClickListener {
            //startActivity(Intent(this, SignInActivity::class.java))
        //}

        // Get Started button click
        btnGetStarted.setOnClickListener {
            // Navigate to menu or next page
            startActivity(Intent(this, Mainpage2::class.java))
        }

        // Bottom navigation listeners
        navHome.setOnClickListener {
            // Already on home
        }

        navMenu.setOnClickListener {
            // Navigate to Menu page
            startActivity(Intent(this, Mainpage2::class.java))
        }

        navOrders.setOnClickListener {
            // Navigate to Orders page
            startActivity(Intent(this, Orders::class.java))
        }

        navAccount.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }
    }

    private fun setAppName() {
        val appName = findViewById<TextView>(R.id.appName)
        val text = "COFF AL."
        val spannableString = SpannableString(text)

        // Orange color for "COFF" (0-4)
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF9800")),
            0,
            4,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // White color for " AL." (4-8)
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FFFFFF")),
            4,
            8,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        appName.text = spannableString
    }

}