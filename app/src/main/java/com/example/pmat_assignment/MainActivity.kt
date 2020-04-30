package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.logic.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userDetails = intent.getSerializableExtra("User") as User

        val fabUserAccount = findViewById<FloatingActionButton>(R.id.fab_userAccount)

        fabUserAccount.setOnClickListener{
            //directs the user to the ModifyAccount layout for them to modify their account details
            val intent = Intent(this, ModifyAccount::class.java)
            intent.putExtra("User", userDetails)
            startActivity(intent)
        }
    }
}
