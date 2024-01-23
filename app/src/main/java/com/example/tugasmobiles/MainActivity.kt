package com.example.tugasmobiles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

@Suppress("IMPLICIT_CAST_TO_ANY")
class MainActivity : AppCompatActivity() {

    private val menuPrices = mapOf(
        "Americano" to 10000,
        "Latte" to 12000,
        "Cappuccino" to 14000,
        "Espresso" to 15000
    )

    private lateinit var editTextName: EditText
    private lateinit var spinnerCoffee: Spinner
    private lateinit var imageViewCoffee: ImageView // Added ImageView reference
    private lateinit var editTextQuantity: EditText
    private lateinit var textViewPrice: TextView
    private lateinit var buttonOrder: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        spinnerCoffee = findViewById(R.id.spinnerCoffee)
        imageViewCoffee = findViewById(R.id.imageViewCoffee) // Initialize ImageView
        editTextQuantity = findViewById(R.id.editTextQuantity)
        textViewPrice = findViewById(R.id.textViewPrice)
        buttonOrder = findViewById(R.id.buttonOrder)

        // Set adapter for Spinner with the list of coffee types
        val coffeeAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.coffee_array,
            android.R.layout.simple_spinner_item
        )
        coffeeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCoffee.adapter = coffeeAdapter

        // Listener to update price and coffee image when selecting a coffee type from the dropdown
        spinnerCoffee.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCoffeeType = spinnerCoffee.selectedItem.toString()
                updatePrice(selectedCoffeeType)
                updateCoffeeImage(selectedCoffeeType)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        buttonOrder.setOnClickListener { orderCoffee() }
    }

    private fun updatePrice(coffeeType: String) {
        // Display coffee price in the TextView
        val price = menuPrices[coffeeType] ?: 0
        textViewPrice.text = "Harga: Rp $price"
    }

    private fun updateCoffeeImage(coffeeType: String) {
        // Dynamically update the coffee image based on the selected coffee type
        val coffeeImageResourceId = when (coffeeType) {
            "Americano" -> {
                R.drawable.americano
            }
            "Latte" -> {
                R.drawable.latte
            }
            "Cappuccino" -> {
                R.drawable.cappucino
            }
            "Espresso" -> {
                R.drawable.espresso
            }
            else -> {}
        }
        imageViewCoffee.setImageResource(coffeeImageResourceId as Int)
    }


    private fun orderCoffee() {
        val name = editTextName.text.toString()
        val coffeeType = spinnerCoffee.selectedItem.toString()
        val quantity = editTextQuantity.text.toString().toIntOrNull() ?: 0
        val price = menuPrices[coffeeType] ?: 0
        val totalPrice = price * quantity

        // Create an Intent to start the OrderActivity
        val intent = Intent(this, OrderActivity::class.java)

        // Pass data to OrderActivity using Intent extras
        intent.putExtra("NAME", name)
        intent.putExtra("COFFEE_TYPE", coffeeType)
        intent.putExtra("QUANTITY", quantity)
        intent.putExtra("TOTAL_PRICE", totalPrice)

        // Start the OrderActivity
        startActivity(intent)

    }
}
