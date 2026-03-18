package com.azores_jc

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AccountActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_account)

        setAppName()

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnSettings = findViewById<ImageView>(R.id.btnSettings)

        val fieldName = findViewById<LinearLayout>(R.id.fieldName)
        val fieldEmail = findViewById<LinearLayout>(R.id.fieldEmail)
        val fieldPhone = findViewById<LinearLayout>(R.id.fieldPhone)
        val fieldAddress = findViewById<LinearLayout>(R.id.fieldAddress)

        val menuOrderHistory = findViewById<LinearLayout>(R.id.menuOrderHistory)
        val menuFavorite = findViewById<LinearLayout>(R.id.menuFavorite)
        val menuMessages = findViewById<LinearLayout>(R.id.menuMessages)
        val menuLogout = findViewById<LinearLayout>(R.id.menuLogout)

        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navMenu = findViewById<LinearLayout>(R.id.navMenu)
        val navOrders = findViewById<LinearLayout>(R.id.navOrders)
        val navAccount = findViewById<LinearLayout>(R.id.navAccount)

        btnBack.setOnClickListener {
            finish()
        }

        btnSettings.setOnClickListener {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
        }

        fieldName.setOnClickListener {
            Toast.makeText(this, "Edit Name", Toast.LENGTH_SHORT).show()
        }

        fieldEmail.setOnClickListener {
            Toast.makeText(this, "Edit Email", Toast.LENGTH_SHORT).show()
        }

        fieldPhone.setOnClickListener {
            Toast.makeText(this, "Edit Phone", Toast.LENGTH_SHORT).show()
        }

        fieldAddress.setOnClickListener {
            Toast.makeText(this, "Add Address", Toast.LENGTH_SHORT).show()
        }

        menuOrderHistory.setOnClickListener {
            Toast.makeText(this, "Order History", Toast.LENGTH_SHORT).show()
        }

        menuFavorite.setOnClickListener {
            Toast.makeText(this, "Favorite", Toast.LENGTH_SHORT).show()
        }

        menuMessages.setOnClickListener {
            Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show()
        }

        // Logout - clear session and go back to SignIn
        menuLogout.setOnClickListener {
            UserSession.registeredUsername = ""
            UserSession.registeredPassword = ""

            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        navHome.setOnClickListener {
            startActivity(Intent(this, Mainpage::class.java))
            finish()
        }

        navMenu.setOnClickListener {
            startActivity(Intent(this, Mainpage2::class.java))
            finish()
        }

        navOrders.setOnClickListener {
            startActivity(Intent(this, Orders::class.java))
            finish()
        }

        navAccount.setOnClickListener {
            // Already on account page
        }
    }

    private fun setAppName() {
        val appName = findViewById<TextView>(R.id.appName)
        val text = "COFF AL."
        val spannableString = SpannableString(text)

        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FF9800")),
            0, 4,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FFFFFF")),
            4, 8,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        appName.text = spannableString
    }
}