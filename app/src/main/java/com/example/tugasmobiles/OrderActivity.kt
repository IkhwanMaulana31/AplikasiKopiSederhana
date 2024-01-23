package com.example.tugasmobiles

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class OrderActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        val textViewName = findViewById<TextView>(R.id.textViewName)
        val textViewCoffeeType = findViewById<TextView>(R.id.textViewCoffeeType)
        val textViewQuantity = findViewById<TextView>(R.id.textViewQuantity)
        val textViewTotalPrice = findViewById<TextView>(R.id.textViewTotalPrice)
        val buttonSave = findViewById<Button>(R.id.buttonSave)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)

        // Retrieve data from Intent extras
        val name = intent.getStringExtra("NAME")
        val coffeeType = intent.getStringExtra("COFFEE_TYPE")
        val quantity = intent.getIntExtra("QUANTITY", 0)
        val totalPrice = intent.getIntExtra("TOTAL_PRICE", 0)

        // Display data in TextViews
        textViewName.text = name
        textViewCoffeeType.text = coffeeType
        textViewQuantity.text = quantity.toString()
        textViewTotalPrice.text = totalPrice.toString()

        // Save data to Firestore
        saveToFirestore(name, coffeeType, quantity, totalPrice)

        // Set click listener for Save button
        buttonSave.setOnClickListener {
            saveToFirestore(name, coffeeType, quantity, totalPrice)

            // Intent to LogoutActivity
            val intent = Intent(this@OrderActivity, LogoutActivity::class.java)
            startActivity(intent)
            finish() // Finish this activity to prevent going back to it with the back button
        }

        // Set click listener for Delete button
        buttonDelete.setOnClickListener {
            deleteFromFirestore(name)
            val intent = Intent(this@OrderActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish this activity to prevent going back to it with the back button
        }
    }

    private fun saveToFirestore(name: String?, coffeeType: String?, quantity: Int, totalPrice: Int) {
        if (name != null && coffeeType != null) {
            val orderData = hashMapOf(
                "coffeeType" to coffeeType,
                "quantity" to quantity,
                "totalPrice" to totalPrice
            )

            // Set document ID to the user's name
            db.collection("menu")
                .document(name)
                .set(orderData)
                .addOnSuccessListener {
                    // Handle success (data saved)
                    println("Document with ID $name added successfully")
                }
                .addOnFailureListener { e ->
                    // Handle failure (data not saved)
                    println("Error adding document: $e")
                }
        }
    }

    private fun deleteFromFirestore(name: String?) {
        if (name != null) {
            db.collection("menu")
                .document(name)
                .delete()
                .addOnSuccessListener {
                    // Handle success (data deleted)
                    println("Document with ID $name deleted successfully")
                }
                .addOnFailureListener { e ->
                    // Handle failure (data not deleted)
                    println("Error deleting document: $e")
                }
        }
    }
}
