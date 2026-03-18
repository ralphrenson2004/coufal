package com.azores_jc

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView

class Mainpage2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide the action bar
        supportActionBar?.hide()

        setContentView(R.layout.activity_mainpage2)

        setAppName()

        // Initialize views
        val cardHotCoffee = findViewById<CardView>(R.id.cardHotCoffee)
        val cardIcedCoffee = findViewById<CardView>(R.id.cardIcedCoffee)
        val cardMilkTea = findViewById<CardView>(R.id.cardMilkTea)
        val cardFrappe = findViewById<CardView>(R.id.cardFrappe)

        // Bottom navigation
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navMenu = findViewById<LinearLayout>(R.id.navMenu)
        val navOrders = findViewById<LinearLayout>(R.id.navOrders)
        val navAccount = findViewById<LinearLayout>(R.id.navAccount)

        // Menu category click listeners
        cardHotCoffee.setOnClickListener {
            // Navigate to Hot Coffee items page
            // TODO: Create HotCoffeeActivity and navigate
            // startActivity(Intent(this, HotCoffeeActivity::class.java))
        }

        cardIcedCoffee.setOnClickListener {
            // Navigate to Iced Coffee items page
            // TODO: Create IcedCoffeeActivity and navigate
        }

        cardMilkTea.setOnClickListener {
            // Navigate to Milk Tea items page
            // TODO: Create MilkTeaActivity and navigate
        }

        cardFrappe.setOnClickListener {
            // Navigate to Frappe items page
            // TODO: Create FrappeActivity and navigate
        }

        // Bottom navigation click listeners
        navHome.setOnClickListener {
            // Navigate to Home/Main page
            startActivity(Intent(this, Mainpage::class.java))
            finish()
        }

        navMenu.setOnClickListener {
            // Already on menu page
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