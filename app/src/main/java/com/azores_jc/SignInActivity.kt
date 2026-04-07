package com.azores_jc

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.text.InputType
import android.widget.EditText
import android.widget.CheckBox
import android.widget.Button
import android.util.Log
import android.widget.Toast

class SignInActivity : AppCompatActivity() {

    private lateinit var btnSignUp: Button
    private lateinit var btnSignIn: Button
    private lateinit var subSignIn: Button
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var cbShowPassword: CheckBox

    // Hardcoded account credentials
    companion object {
        private const val HARDCODED_USERNAME = "admin"
        private const val HARDCODED_PASSWORD = "password123"
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

        // Submit Sign In with if/else validation
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

        if (username.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Please enter your username and password", Toast.LENGTH_SHORT).show()
        } else if (username.isEmpty()) {
            Toast.makeText(this, "Please enter your username", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show()
        } else if (
        // ✅ Check against registered credentials OR hardcoded account
            (username == UserSession.registeredUsername && password == UserSession.registeredPassword) ||
            (username == HARDCODED_USERNAME && password == HARDCODED_PASSWORD)
        ) {
            Log.d("SignInActivity", "Sign In successful")
            Toast.makeText(this, "Sign In Successful!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Mainpage::class.java))
            finish()
        } else {
            Log.d("SignInActivity", "Invalid credentials entered")
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
        }
    }
}
