package com.mycode.ticketbookingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth



class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        findViewById<Button>(R.id.Login).setOnClickListener{
            performLogin()
        }

        findViewById<TextView>(R.id.SignupPage).setOnClickListener{
            val intent= Intent(this,Signup::class.java)
            startActivity(intent)
        }
    }

    private fun performLogin(){
        val email=findViewById<EditText>(R.id.EmailId1).text.toString()
        val password=findViewById<EditText>(R.id.password1).text.toString()
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter you email address or password", Toast.LENGTH_LONG)
                .show()
            return
        }
        Log.d("Login","Email and Password: "+email+"$password")

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener
                Toast.makeText(this,"Logged Successfully",Toast.LENGTH_LONG).show()
                Log.d("Login","${it.result?.user?.uid}")
                val intent= Intent(this,HomePage::class.java)
                intent.flags= Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)


            }

            .addOnFailureListener{
                Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
            }


    }

}