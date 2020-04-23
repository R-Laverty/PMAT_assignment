package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
                //TODO display main view
            }
            else {
                //TODO display user/password not recognised text
            }
        }
    }
}
