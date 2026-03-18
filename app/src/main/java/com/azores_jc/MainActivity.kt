package com.azores_jc

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnSignUp: Button
    private lateinit var btnSignIn: Button
    private lateinit var subSignUp: Button
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var cbShowPassword: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        // ✅ FIX: use correct layout file name
        setContentView(R.layout.mainactivity)

        // ✅ Initialize views
        btnSignUp = findViewById(R.id.btnSignUp)
        btnSignIn = findViewById(R.id.btnSignIn)
        subSignUp = findViewById(R.id.subSignUp)
        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        etConfirmPassword = findViewById(R.id.etConfirmPassword)
        cbShowPassword = findViewById(R.id.cbShowPassword)

        Log.d("MainActivity", "Views initialized")

        // Sign Up tab (current screen)
        btnSignUp.setOnClickListener {
            Toast.makeText(this, "Already on Sign Up screen", Toast.LENGTH_SHORT).show()
        }

        // Sign In tab
        btnSignIn.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        // Submit Sign Up - Navigate to Mainpage on success
        subSignUp.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            val confirm = etConfirmPassword.text.toString()

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirm) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Save credentials to UserSession
            UserSession.registeredUsername = username
            UserSession.registeredPassword = password

            Toast.makeText(this, "Sign Up Successful! Please sign in.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Show / Hide password (both fields)
        cbShowPassword.setOnCheckedChangeListener { _, isChecked ->
            val type = if (isChecked)
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            else
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            etPassword.inputType = type
            etConfirmPassword.inputType = type

            etPassword.setSelection(etPassword.text.length)
            etConfirmPassword.setSelection(etConfirmPassword.text.length)
        }
    }
}