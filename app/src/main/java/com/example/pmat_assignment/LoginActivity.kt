package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.logic.validateSignIn

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //setContentView(R.layout.activity_user_details_p1)
        //setContentView(R.layout.activity_user_details_p2)

        val btnRegister = findViewById<Button>(R.id.btn_register)
        val btnSignIn = findViewById<Button>(R.id.btn_login)

        btnRegister.setOnClickListener{
            val intent = Intent(this, UserDetailsP1::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener{
            val username: String = findViewById<EditText>(R.id.txt_username).text.toString()
            val password: String = findViewById<EditText>(R.id.password).text.toString()
            if (validateSignIn(username, password)) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else {
                findViewById<TextView>(R.id.txt_error).text = getString(R.string.invalid_login)
            }
        }
    }
}
