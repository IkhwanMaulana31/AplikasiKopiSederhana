package com.example.tugasmobiles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class LogoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        val buttonKeluar = findViewById<Button>(R.id.buttonKeluar)

        // Keluar button click listener
        buttonKeluar.setOnClickListener {
            // Show a toast message indicating the user is logging out
            Toast.makeText(this@LogoutActivity, "Anda telah keluar", Toast.LENGTH_SHORT).show()

            // Intent to LoginActivity
            val intent = Intent(this@LogoutActivity, LoginActivity::class.java)
            startActivity(intent)
            finishAffinity() // Finish all activities to prevent going back to them with the back button
        }

    }
}