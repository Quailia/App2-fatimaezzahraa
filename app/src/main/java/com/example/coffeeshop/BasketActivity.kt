package com.example.coffeeshop

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class BasketActivity : AppCompatActivity() {

    private lateinit var basket: MutableList<String>
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var emptyBasketMessage: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)

        basket = (applicationContext as CoffeeShopApp).basket

        emptyBasketMessage = findViewById(R.id.emptyBasketMessage)
        val basketListView: ListView = findViewById(R.id.basketListView)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, basket)
        basketListView.adapter = adapter

        updateEmptyBasketMessage()

        val orderButton: Button = findViewById(R.id.orderButton)
        orderButton.setOnClickListener {
            showOrderConfirmation()
        }

        val returnToShopButton: Button = findViewById(R.id.returnToShopButton)
        returnToShopButton.setOnClickListener {
            finish()
        }
    }

    private fun showOrderConfirmation() {
        val orderSummary = basket.joinToString("\n")
        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Order Confirmation")
            .setMessage("Thank you for buying from us!\n\n Order Summary:\n$orderSummary")
            .setPositiveButton("OK") { _, _ ->
                basket.clear()
                adapter.notifyDataSetChanged()
                updateEmptyBasketMessage()
            }
            .create()
        alertDialog.show()
    }

    private fun updateEmptyBasketMessage() {
        if (basket.isEmpty()) {
            emptyBasketMessage.visibility = TextView.VISIBLE
        } else {
            emptyBasketMessage.visibility = TextView.GONE
        }
    }
}
