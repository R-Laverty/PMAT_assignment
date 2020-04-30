package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.logic.User
import com.example.logic.SignIn
import com.example.logic.validateSignIn

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var userDetails = User(null,null,null,null,null,null,null,null,null,null, null,null,null,null)

        val btnRegister = findViewById<Button>(R.id.btn_register)
        val btnSignIn = findViewById<Button>(R.id.btn_login)

        btnRegister.setOnClickListener{
            //directs the user to the registration layouts
            val intent = Intent(this, UserDetailsP1::class.java)
            intent.putExtra("User", userDetails)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener{
            //checks the user exists on the database then retrives their details if they do
            // otherwise displays username/password incorrect
            val username: String = findViewById<EditText>(R.id.txt_username).text.toString()
            val password: String = findViewById<EditText>(R.id.password).text.toString()

            if (validateSignIn(username, password)) {
                SignIn(username,password,userDetails)
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("User", userDetails)
                startActivity(intent)
            }
            else {
                findViewById<TextView>(R.id.txt_error).text = getString(R.string.invalid_login)
            }
        }
    }
}
