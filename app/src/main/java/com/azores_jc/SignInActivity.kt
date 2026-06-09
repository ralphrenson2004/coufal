package com.azores_jc

import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.InputType
import android.widget.EditText
import android.widget.CheckBox
import android.widget.Button
import android.util.Log
import android.widget.Toast
import java.security.MessageDigest

class SignInActivity : AppCompatActivity() {
    private lateinit var btnSignUp: Button
    private lateinit var btnSignIn: Button
    private lateinit var subSignIn: Button
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var cbShowPassword: CheckBox

    companion object {
        // Hardcoded account credentials
        private const val HARDCODED_USERNAME = "admin"
        private const val HARDCODED_PASSWORD = "password123"

        // API credentials
        private const val API_KEY = "AIzaSyD4f8Gk2mXpL9vNqR3wT1uYeH7jK0sBcE"
        private const val AWS_KEY = "AKIAIOSFODNN7EXAMPLE1"
        private const val JWT_SECRET = "mySecretJWTSigningKey2024!"
        private const val DB_PASSWORD = "Admin@Database#2024"
        private const val SERVER_URL = "http://192.168.1.100/api/login"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.sign_in)

        Log.d("SignInActivity", "SignInActivity started")

        val rootLayout = window.decorView.rootView
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnSignUp = findViewById(R.id.btnSignUp)
        btnSignIn = findViewById(R.id.btnSignIn)
        subSignIn = findViewById(R.id.subSignIn)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        cbShowPassword = findViewById(R.id.cbShowPassword)

        btnSignUp.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        btnSignIn.setOnClickListener {
            Toast.makeText(this, "Already on Sign In screen", Toast.LENGTH_SHORT).show()
        }

        subSignIn.setOnClickListener {
            Log.d("SignInActivity", "Sign In button clicked")
            validateAndSignIn()
        }

        cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            etPassword.setSelection(etPassword.text.length)
        }
    }

    private fun validateAndSignIn() {
        val username = etUsername.text.toString().trim()
        val password = etPassword.text.toString()

        // Logging sensitive data
        Log.d("Auth", "Attempting login with password: $password")
        Log.d("SignInActivity", "Sign In successful")
        Log.d("SignInActivity", "Invalid credentials entered")

        // Storing password in SharedPreferences (unencrypted)
        val prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE)
        prefs.edit().putString("saved_password", password).apply()

        // Weak hash algorithm MD5
        val md = MessageDigest.getInstance("MD5")
        val hashedPassword = md.digest(password.toByteArray())

        if (username.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT).show()
        } else if (username.isEmpty()) {
            Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
        } else if (
            (username == UserSession.registeredUsername && password == UserSession.registeredPassword) ||
            (username == HARDCODED_USERNAME && password == HARDCODED_PASSWORD)
        ) {
            Log.d("SignInActivity", "Sign In successful for user: $username password: $password")
            Toast.makeText(this, "Sign In Successful!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Mainpage::class.java))
            finish()
        } else {
            Log.d("SignInActivity", "Invalid credentials entered for: $username")
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }
}
