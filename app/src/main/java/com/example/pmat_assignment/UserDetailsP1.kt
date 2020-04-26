package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.widget.Button
import android.widget.EditText
import com.example.logic.User
import com.example.logic.validateAgeAndDOB
import com.example.logic.validateEmail

class UserDetailsP1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_p1)

        val btnReturn = findViewById<Button>(R.id.btn_return)
        val btnNext = findViewById<Button>(R.id.btn_next)
        var userDetails = User(null,null,null,null,null,null,null)

        btnReturn.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener{
            /*try {
                val firstname = findViewById<EditText>(R.id.etxt_firstname).text.toString()
                val email = findViewById<EditText>(R.id.etxt_email).text.toString()
                val dayOfBirth = findViewById<EditText>(R.id.etxt_dob_day).text.toString().toInt()
                val monthOfBirth =
                    findViewById<EditText>(R.id.etxt_dob_month).text.toString().toInt()
                val yearOfBirth = findViewById<EditText>(R.id.etxt_dob_year).text.toString().toInt()
                val age = findViewById<EditText>(R.id.etxt_email).text.toString().toInt()

                if (!validateEmail(email)) {
                    //TODO inform user email is not valid
                } else if (!validateAgeAndDOB(dayOfBirth, monthOfBirth, yearOfBirth, age)) {
                    //TODO inform user something is wrong with their DOB
                } else {*/
                    val firstname = findViewById<EditText>(R.id.etxt_firstname).text.toString()
                    userDetails.mfirstName = firstname

                    val intent = Intent(this, UserDetailsP2::class.java)
                    intent.putExtra("User", userDetails)
                    startActivity(intent)
/*                }
            } catch (e: java.lang.NumberFormatException) {
                //TODO display message that all fields must be filled in
                val intent = Intent(this, UserDetailsP2::class.java)
                startActivity(intent)
            }*/
        }
    }
}
