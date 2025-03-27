package com.loukya.day16database

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class MainActivity : AppCompatActivity() {

    lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val signupbtn = findViewById<Button>(R.id.signupbtn)
        val email = findViewById<EditText>(R.id.emailet)
        val password = findViewById<EditText>(R.id.passwordet)
        val name = findViewById<EditText>(R.id.nameet)
        val uniqueId = findViewById<EditText>(R.id.uniqueId)

        signupbtn.setOnClickListener {

            val nameText = name.text.toString()
            val emailText = email.text.toString()
            val uniqueid = uniqueId.text.toString()
            val passwordd = password.text.toString()

            val user = User(nameText,emailText,passwordd,uniqueid)
            database= FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueid).setValue(user).addOnSuccessListener {
                name.text.clear()
                email.text.clear()
                password.text.clear()
                uniqueId.text.clear()
                Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText(this,"Registration failed", Toast.LENGTH_SHORT).show()
            }

        }



    }
}