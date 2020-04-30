package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AndroidRuntimeException
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import android.widget.TextView
import com.example.logic.User
import com.example.logic.validateAgeAndDOB
import com.example.logic.validateEmail
import java.util.*

class UserDetailsP1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_p1)

        var userDetails = intent.getSerializableExtra("User") as User

        val btnReturn = findViewById<Button>(R.id.btn_return)
        val btnNext = findViewById<Button>(R.id.btn_next)

        btnReturn.setOnClickListener{
            //redirects the user to the LoginActivity layout
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnNext.setOnClickListener{
            /*grabs the entered userdetails from the layout and puts them into the userdetails
            object. If any if the fields are null the exception will be caught and the user will
            be asked to ensure all field are filled in, otherwise email and age/DOB are validated
            and if they are valid the user is directed to the next registration page*/
            var errortxt = ""
            try {
                val firstname = findViewById<EditText>(R.id.etxt_firstname).text.toString()
                val surname = findViewById<EditText>(R.id.etxt_surname).text.toString()
                val email = findViewById<EditText>(R.id.etxt_email).text.toString()
                val dayOfBirth = findViewById<EditText>(R.id.etxt_dob_day).text.toString().toInt()
                val monthOfBirth =
                    findViewById<EditText>(R.id.etxt_dob_month).text.toString().toInt()
                val yearOfBirth = findViewById<EditText>(R.id.etxt_dob_year).text.toString().toInt()
                val age = findViewById<EditText>(R.id.etxt_age).text.toString().toInt()
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)

                if (!validateEmail(email)) {
                    errortxt = getString(R.string.invalid_email)
                } else if (!validateAgeAndDOB(dayOfBirth, monthOfBirth, yearOfBirth, age, currentYear)) {
                    errortxt = getString(R.string.invalid_DOB_Age)
                } else {
                    userDetails.mfirstName = firstname
                    userDetails.msurname = surname
                    userDetails.memail = email
                    userDetails.mdobDay = dayOfBirth
                    userDetails.mdobMonth = monthOfBirth
                    userDetails.mdobYear = yearOfBirth
                    userDetails.mage = age

                    val intent = Intent(this, UserDetailsP2::class.java)
                    intent.putExtra("User", userDetails)
                    startActivity(intent)
                }
            } catch (e: java.lang.NumberFormatException) {
                errortxt = getString(R.string.prompt_fill_all_fields)
            }
            findViewById<TextView>(R.id.txt_errorUDP1).text = errortxt
        }
    }
}
