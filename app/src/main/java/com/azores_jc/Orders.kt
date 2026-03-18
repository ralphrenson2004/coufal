package com.azores_jc

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan

class Orders : AppCompatActivity() {

    private var quantity = 1
    private var selectedSugar = "100%"
    private var selectedSize = "M"

    // Sugar level views
    private lateinit var sugar0: LinearLayout
    private lateinit var sugar25: LinearLayout
    private lateinit var sugar50: LinearLayout
    private lateinit var sugar75: LinearLayout
    private lateinit var sugar100: LinearLayout

    // Size views
    private lateinit var sizeS: LinearLayout
    private lateinit var sizeM: LinearLayout
    private lateinit var sizeL: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide action bar
        supportActionBar?.hide()

        setContentView(R.layout.activity_orders)

        setAppName()

        // Initialize views
        val tvQuantity = findViewById<TextView>(R.id.tvQuantity)
        val btnMinus = findViewById<android.widget.Button>(R.id.btnMinus)
        val btnPlus = findViewById<android.widget.Button>(R.id.btnPlus)
        val fabAddToCart = findViewById<FloatingActionButton>(R.id.fabAddToCart)

        // Sugar level options
        sugar0 = findViewById(R.id.sugar0)
        sugar25 = findViewById(R.id.sugar25)
        sugar50 = findViewById(R.id.sugar50)
        sugar75 = findViewById(R.id.sugar75)
        sugar100 = findViewById(R.id.sugar100)

        // Size options
        sizeS = findViewById(R.id.sizeS)
        sizeM = findViewById(R.id.sizeM)
        sizeL = findViewById(R.id.sizeL)

        // Bottom navigation
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navMenu = findViewById<LinearLayout>(R.id.navMenu)
        val navOrders = findViewById<LinearLayout>(R.id.navOrders)
        val navAccount = findViewById<LinearLayout>(R.id.navAccount)

        // Quantity controls
        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                tvQuantity.text = quantity.toString()
            }
        }

        btnPlus.setOnClickListener {
            quantity++
            tvQuantity.text = quantity.toString()
        }

        // Sugar level selection
        sugar0.setOnClickListener {
            clearSugarSelection()
            sugar0.isSelected = true
            selectedSugar = "0%"
            Toast.makeText(this, "Sugar: 0%", Toast.LENGTH_SHORT).show()
        }

        sugar25.setOnClickListener {
            clearSugarSelection()
            sugar25.isSelected = true
            selectedSugar = "25%"
            Toast.makeText(this, "Sugar: 25%", Toast.LENGTH_SHORT).show()
        }

        sugar50.setOnClickListener {
            clearSugarSelection()
            sugar50.isSelected = true
            selectedSugar = "50%"
            Toast.makeText(this, "Sugar: 50%", Toast.LENGTH_SHORT).show()
        }

        sugar75.setOnClickListener {
            clearSugarSelection()
            sugar75.isSelected = true
            selectedSugar = "75%"
            Toast.makeText(this, "Sugar: 75%", Toast.LENGTH_SHORT).show()
        }

        sugar100.setOnClickListener {
            clearSugarSelection()
            sugar100.isSelected = true
            selectedSugar = "100%"
            Toast.makeText(this, "Sugar: 100%", Toast.LENGTH_SHORT).show()
        }

        // Size selection
        sizeS.setOnClickListener {
            clearSizeSelection()
            sizeS.isSelected = true
            selectedSize = "S"
            Toast.makeText(this, "Size: Small", Toast.LENGTH_SHORT).show()
        }

        sizeM.setOnClickListener {
            clearSizeSelection()
            sizeM.isSelected = true
            selectedSize = "M"
            Toast.makeText(this, "Size: Medium", Toast.LENGTH_SHORT).show()
        }

        sizeL.setOnClickListener {
            clearSizeSelection()
            sizeL.isSelected = true
            selectedSize = "L"
            Toast.makeText(this, "Size: Large", Toast.LENGTH_SHORT).show()
        }

        // Add to cart
        fabAddToCart.setOnClickListener {
            Toast.makeText(
                this,
                "Added $quantity item(s) - Size: $selectedSize, Sugar: $selectedSugar",
                Toast.LENGTH_LONG
            ).show()
        }

        // Bottom navigation
        navHome.setOnClickListener {
            startActivity(Intent(this, Mainpage::class.java))
            finish()
        }

        navMenu.setOnClickListener {
            startActivity(Intent(this, Mainpage2::class.java))
            finish()
        }

        navOrders.setOnClickListener {
            // Already on orders page
        }

        navAccount.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }

        // Set default selections
        sugar100.isSelected = true
        sizeM.isSelected = true
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
    private fun clearSugarSelection() {
        sugar0.isSelected = false
        sugar25.isSelected = false
        sugar50.isSelected = false
        sugar75.isSelected = false
        sugar100.isSelected = false
    }

    private fun clearSizeSelection() {
        sizeS.isSelected = false
        sizeM.isSelected = false
        sizeL.isSelected = false
    }
}