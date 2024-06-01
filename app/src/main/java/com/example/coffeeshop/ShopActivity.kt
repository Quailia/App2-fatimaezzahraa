package com.example.coffeeshop

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ShopActivity : AppCompatActivity() {

    private val drinks = arrayOf("Latte", "Cappuccino", "Espresso", "Mocha")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val drinksListView: ListView = findViewById(R.id.drinksListView)
        val adapter = DrinkAdapter(this, drinks)
        drinksListView.adapter = adapter

        drinksListView.setOnItemClickListener { _, _, position, _ ->
            val selectedDrink = drinks[position]
            val intent = Intent(this, DrinkDetailActivity::class.java)
            intent.putExtra("DRINK_NAME", selectedDrink)
            startActivity(intent)
        }

        val viewBasketButton: Button = findViewById(R.id.viewBasketButton)
        viewBasketButton.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }
    }

    private class DrinkAdapter(context: Context, private val drinks: Array<String>) :
        ArrayAdapter<String>(context, 0, drinks) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_drink, parent, false)
            val drinkNameTextView: TextView = view.findViewById(R.id.drinkNameTextView)
            val drinkBackgroundImageView: ImageView = view.findViewById(R.id.drinkBackgroundImageView)

            drinkNameTextView.text = drinks[position]
            drinkBackgroundImageView.setImageResource(getBackgroundResource(drinks[position]))

            return view
        }

        private fun getBackgroundResource(drinkName: String): Int {
            return when (drinkName) {
                "Latte" -> R.drawable.ic_latte_background
                "Cappuccino" -> R.drawable.ic_cappuccino_background
                "Espresso" -> R.drawable.ic_espresso_background
                "Mocha" -> R.drawable.ic_mocha_background
                else -> R.drawable.ic_coffee_background // Default background
            }
        }
    }
}
