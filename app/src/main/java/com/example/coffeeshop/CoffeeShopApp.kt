package com.example.coffeeshop

import android.app.Application

class CoffeeShopApp : Application() {
    val basket: MutableList<String> = mutableListOf()
}
