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


class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        findViewById<Button>(R.id.Signup).setOnClickListener{
            performSignup()
        }

        findViewById<TextView>(R.id.loginPage).setOnClickListener{
            val intent= Intent(this,Login::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    private fun performSignup(){
        val username=findViewById<EditText>(R.id.username).text.toString()
        val email=findViewById<EditText>(R.id.EmailId).text.toString()
        val password=findViewById<EditText>(R.id.password).text.toString()

        if(email.isEmpty()|| password.isEmpty()){
            Toast.makeText(this,"Please enter you email address or password",Toast.LENGTH_LONG).show()
            return
        }
        Log.d("SignUp","Email and Password: "+email+"$password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if(!it.isSuccessful) return@addOnCompleteListener
                Toast.makeText(this,"Welcome "+username+"!",Toast.LENGTH_LONG).show()
                Log.d("SignUp","${it.result?.user?.uid}")
                val intent= Intent(this,HomePage::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }

            .addOnFailureListener{
                Toast.makeText(this,"${it.message}",Toast.LENGTH_LONG).show()
            }
    }
}