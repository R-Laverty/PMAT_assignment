package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        btnReturn.setOnClickListener{
            val intent = Intent(this, UserDetailsP2::class.java)
            intent.putExtra("User", userDetails)
            intent.putExtra("aLogic", mLogic)
            startActivity(intent)
        }

        btnNext.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("User", userDetails)
            intent.putExtra("aLogic", mLogic)
            startActivity(intent)
        }
    }
}
