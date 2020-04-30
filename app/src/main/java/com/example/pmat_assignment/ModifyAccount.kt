package com.example.pmat_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.logic.User
import com.example.logic.updateDatabase

class ModifyAccount : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modify_account)

        var userDetails = intent.getSerializableExtra("User") as User

        val btnSaveAndReturn = findViewById<Button>(R.id.btn_saveAndReturn)

        btnSaveAndReturn.setOnClickListener{
            //saves the modified user details to userdetails then updates the database and redirects
            // to the MainActivity
            //TODO grab all the details from the layout and update userdetails
            updateDatabase(userDetails)
        }
    }
}
