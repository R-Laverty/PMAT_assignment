package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.logic.Logic
import com.example.logic.User

class UserDetailsBIO : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_bio)

        var userDetails = intent.getSerializableExtra("User") as User
        var mLogic = intent.getSerializableExtra("aLogic") as Logic

        val btn_return_BIO = findViewById<Button>(R.id.btn_return_BIO)
        val btn_next_BIO = findViewById<Button>(R.id.btn_next_BIO)

        btn_next_BIO.setOnClickListener{
            userDetails.mbio = findViewById<EditText>(R.id.eBIO).text.toString()
            if (userDetails.mbio != null || userDetails.mbio != ""){
                val intent = Intent(this, UserDetailsP3::class.java)
                intent.putExtra("User", userDetails)
                intent.putExtra("aLogic", mLogic)
                startActivity(intent)
            }else{

            }
        }

        btn_return_BIO.setOnClickListener{
            val intent = Intent(this, UserDetailsP2::class.java)
            intent.putExtra("User", userDetails)
            intent.putExtra("aLogic", mLogic)
            startActivity(intent)
        }
    }
}
