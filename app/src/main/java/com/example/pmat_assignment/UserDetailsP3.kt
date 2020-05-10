package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import com.example.logic.Logic
import com.example.logic.User
import kotlinx.android.synthetic.main.activity_user_details_p3.*

class UserDetailsP3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_p3)

        var userDetails = intent.getSerializableExtra("User") as User
        var mLogic = intent.getSerializableExtra("aLogic") as Logic

        val btnReturn = findViewById<Button>(R.id.btn_return)
        val btnNext = findViewById<Button>(R.id.btn_nextP3)

        val etxtH1 = findViewById<EditText>(R.id.hobbies_1)
        val etxtH2 = findViewById<EditText>(R.id.txt_hobbies_2_entry)
        val etxtH3 = findViewById<EditText>(R.id.txt_hobbies_3_entry)

        btnReturn.setOnClickListener{
            val intent = Intent(this, UserDetailsBIO::class.java)
            intent.putExtra("User", userDetails)
            intent.putExtra("aLogic", mLogic)
            startActivity(intent)
        }

        btnNext.setOnClickListener{
            userDetails.mhobbie1 = mLogic.formatForDatabase(etxtH1.text.toString())
            userDetails.mhobbie2 = mLogic.formatForDatabase(etxtH2.text.toString())
            userDetails.mhobbie3 = mLogic.formatForDatabase(etxtH3.text.toString())

            val policy: StrictMode.ThreadPolicy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            mLogic.updateDatabase(userDetails)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("User", userDetails)
            intent.putExtra("aLogic", mLogic)
            startActivity(intent)
        }
    }
}
