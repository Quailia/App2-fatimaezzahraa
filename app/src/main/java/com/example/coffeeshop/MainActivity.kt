package com.example.coffeeshop

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUpButton: Button = findViewById(R.id.signUpButton)
        signUpButton.setOnClickListener {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }

        val infoButton = findViewById<ImageButton>(R.id.btnInfo)
        infoButton.setOnClickListener { showInfoDialog() }
    }
    private fun showInfoDialog() {
        AlertDialog.Builder(this)
            .setTitle("About Sweet Café")
            .setMessage("Welcome to the Sweet Café app! Here you can browse and customize your favorite coffee drinks, add them to your basket, and place an order. Enjoy your coffee today.")
            .setPositiveButton("OK", null)
            .show()
    }
}