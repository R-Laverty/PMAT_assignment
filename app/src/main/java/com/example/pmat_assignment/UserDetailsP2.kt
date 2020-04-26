package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.logic.User

class UserDetailsP2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_p2)

        val btnReturn = findViewById<Button>(R.id.btn_return)
        val btnNext = findViewById<Button>(R.id.btn_nextp2)
        var userDetails = intent.getSerializableExtra("User") as? User

        btnReturn.setOnClickListener{
            val intent = Intent(this, UserDetailsP1::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener{
            val intent = Intent(this, UserDetailsP3::class.java)
            startActivity(intent)
            //TODO direct to the next registration page also use a try catch to detect if all fields
            // are filled in prompt user if some are empty
        }
    }
}
