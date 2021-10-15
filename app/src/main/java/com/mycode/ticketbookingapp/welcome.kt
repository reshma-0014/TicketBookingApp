package com.mycode.ticketbookingapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mycode.ticketbookingapp.databinding.ActivityWelcomeBinding

class welcome : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar!!.hide()
        binding.btngoogle.setOnClickListener(View.OnClickListener { signIn() })

    }

    
}