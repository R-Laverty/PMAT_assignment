package com.example.pmat_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
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

        val matches = mLogic.getMatches(userDetails)
        var currentMatch = 0

        var bio = "Age:\nGender: ${matches[currentMatch].mgender}\nSexuality: ${matches[currentMatch].msexuality}\n" +
                "Relationship type: ${matches[currentMatch].mRtype}\n\nBio: ${matches[currentMatch].mbio}\n\n" +
                "Hobbie 1: ${matches[currentMatch].mhobbie1}\nHobbie 2: ${matches[currentMatch].mhobbie2}\n" +
                "Hobbie 3: ${matches[currentMatch].mhobbie3}"

        val txtName = findViewById<TextView>(R.id.textView)
        val fabUserAccount = findViewById<FloatingActionButton>(R.id.fab_userAccount)
        val fabLikeMatch = findViewById<FloatingActionButton>(R.id.floatingActionButton2)
        val fabDislikeMatch = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        val bioText = findViewById<TextView>(R.id.textView3)
        bioText.text = bio
        txtName.text = matches[currentMatch].mfirstName

        fabUserAccount.setOnClickListener{
            //directs the user to the ModifyAccount layout for them to modify their account details
            val intent = Intent(this, ModifyAccount::class.java)
            intent.putExtra("aLogic", mLogic)
            intent.putExtra("User", userDetails)
            startActivity(intent)
        }

        fabLikeMatch.setOnClickListener{
            currentMatch = currentMatch++
            if (matches.getOrNull(currentMatch) != null){
                bio = "Age:\nGender: ${matches[currentMatch].mgender}\nSexuality: ${matches[currentMatch].msexuality}\n" +
                        "Relationship type: ${matches[currentMatch].mRtype}\n\nBio: ${matches[currentMatch].mbio}\n\n" +
                        "Hobbie 1: ${matches[currentMatch].mhobbie1}\nHobbie 2: ${matches[currentMatch].mhobbie2}\n" +
                        "Hobbie 3: ${matches[currentMatch].mhobbie3}"
                bioText.text = bio
            }
        }
    }
}
