package com.example.coffeeshop

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DrinkDetailActivity : AppCompatActivity() {

    private lateinit var basket: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_detail)

        val drinkName = intent.getStringExtra("DRINK_NAME")
        val drinkNameTextView: TextView = findViewById(R.id.drinkNameTextView)
        val bannerImageView: ImageView = findViewById(R.id.bannerImageView)

        drinkNameTextView.text = drinkName
        bannerImageView.setImageResource(getBannerResource(drinkName))

        val sizeRadioGroup: RadioGroup = findViewById(R.id.sizeRadioGroup)
        val quantitySpinner: Spinner = findViewById(R.id.quantitySpinner)
        val addToBasketButton: Button = findViewById(R.id.addToBasketButton)

        val quantities = arrayOf(1, 2, 3, 4, 5)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, quantities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        quantitySpinner.adapter = adapter

        basket = (applicationContext as CoffeeShopApp).basket

        addToBasketButton.setOnClickListener {
            val selectedSize = when (sizeRadioGroup.checkedRadioButtonId) {
                R.id.smallRadioButton -> "Small"
                R.id.mediumRadioButton -> "Medium"
                R.id.largeRadioButton -> "Large"
                else -> "Unknown Size"
            }
            val selectedQuantity = quantitySpinner.selectedItem.toString()
            val order = "$selectedQuantity x $selectedSize $drinkName"
            basket.add(order)
            finish()
        }

        val returnToShopButton: Button = findViewById(R.id.returnToShopButton)
        returnToShopButton.setOnClickListener {
            finish()
        }
    }

    private fun getBannerResource(drinkName: String?): Int {
        return when (drinkName) {
            "Latte" -> R.drawable.ic_latte_banner
            "Cappuccino" -> R.drawable.ic_cappuccino_banner
            "Espresso" -> R.drawable.ic_espresso_banner
            "Mocha" -> R.drawable.ic_mocha_banner
            else -> R.drawable.ic_shop_banner
        }
    }
}
