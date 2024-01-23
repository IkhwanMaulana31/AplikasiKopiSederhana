package com.example.tugasmobiles

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val validUsername = "admin"
    private val validPassword = "admin"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextUsername: EditText = findViewById(R.id.editTextUsername)
        val editTextPassword: EditText = findViewById(R.id.editTextPassword)
        val buttonLogin: Button = findViewById(R.id.buttonLogin)
        val textViewForgotPassword: TextView = findViewById(R.id.textViewForgotPassword)
        val textViewRegister: TextView = findViewById(R.id.textViewRegister)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            if (isValidCredentials(username, password)) {
                // Login berhasil, arahkan ke halaman Menu
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Login gagal, berikan pesan error
                // Anda bisa menampilkan pesan error menggunakan Toast atau TextView
            }
        }

        textViewForgotPassword.setOnClickListener {
            // Implementasi fitur lupa password di sini
            // Anda bisa membuka halaman baru atau menampilkan dialog untuk mereset password
        }

        textViewRegister.setOnClickListener {
            // Implementasi fitur register di sini
            // Redirect ke halaman register atau tampilkan dialog registrasi
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return username == validUsername && password == validPassword
    }
}
