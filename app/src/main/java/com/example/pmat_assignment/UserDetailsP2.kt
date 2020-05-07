package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.logic.Logic
import com.example.logic.User

class UserDetailsP2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_p2)

        var userDetails = intent.getSerializableExtra("User") as User
        var mLogic = intent.getSerializableExtra("aLogic") as Logic

        val btnReturn = findViewById<Button>(R.id.btn_return)
        val btnNext = findViewById<Button>(R.id.btn_nextp2)

        btnReturn.setOnClickListener{
            //returns the user to the last registration page
            val intent = Intent(this, UserDetailsP1::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener{
            /*grabs the entered details from the activity and updates the userdetails object unless
            any of the fields are null*/
            //TODO this try catch will not work replace with an if == "" and do the same in regP1
            try {
                userDetails.mpassword = findViewById<EditText>(R.id.etxt_password).text.toString()
                userDetails.mgender = findViewById<EditText>(R.id.etxt_gender).text.toString()
                userDetails.msexuality = findViewById<EditText>(R.id.etxt_sexuality).text.toString()
                val intent = Intent(this, UserDetailsP3::class.java)
                intent.putExtra("User", userDetails)
                intent.putExtra("aLogic", mLogic)
                startActivity(intent)
            }catch (e: java.lang.NumberFormatException) {
                //TODO send fill all fields error message
            }
        }
    }
}
