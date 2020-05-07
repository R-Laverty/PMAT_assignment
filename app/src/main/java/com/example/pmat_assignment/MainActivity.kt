package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.logic.Logic
import com.example.logic.User
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userDetails = intent.getSerializableExtra("User") as User
        var mLogic = intent.getSerializableExtra("aLogic") as Logic
        val bio = "Age:\nGender: ${userDetails.mgender}\nSexuality: ${userDetails.msexuality}\n" +
                "Relationship type: ${userDetails.mRtype}\n\nBio: ${userDetails.mbio}\n\n" +
                "Hobbie 1: ${userDetails.mhobbie1}\nHobbie 2: ${userDetails.mhobbie2}\n" +
                "Hobbie 3: ${userDetails.mhobbie3}"

        val fabUserAccount = findViewById<FloatingActionButton>(R.id.fab_userAccount)
        val bioText = findViewById<TextView>(R.id.textView3)
        bioText.text = bio

        fabUserAccount.setOnClickListener{
            //directs the user to the ModifyAccount layout for them to modify their account details
            val intent = Intent(this, ModifyAccount::class.java)
            intent.putExtra("aLogic", mLogic)
            intent.putExtra("User", userDetails)
            startActivity(intent)
        }
    }
}
