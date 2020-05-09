package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.logic.Logic
import com.example.logic.User

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var userDetails = User(null,null,null,null,null,
            null,null,null,null,null, null,
            null,null,null, null, null, null,
            null,null)
        var mLogic = Logic()

        val btnRegister = findViewById<Button>(R.id.btn_register)
        val btnSignIn = findViewById<Button>(R.id.btn_login)

        btnRegister.setOnClickListener{
            //directs the user to the registration layouts
            val intent = Intent(this, UserDetailsP1::class.java)
            intent.putExtra("User", userDetails)
            intent.putExtra("aLogic", mLogic)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener{
            //checks the user exists on the database then retrives their details if they do
            // otherwise displays username/password incorrect
            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
            val username: String = findViewById<EditText>(R.id.txt_username).text.toString()
            val password: String = findViewById<EditText>(R.id.password).text.toString()

                userDetails = mLogic.SignIn(username,password,userDetails)
            if (userDetails.mfirstName != null){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("aLogic", mLogic)
                intent.putExtra("User", userDetails)
                startActivity(intent)
            }
            else {
                findViewById<TextView>(R.id.txt_error).text = getString(R.string.invalid_login)
            }
        }
    }
}
