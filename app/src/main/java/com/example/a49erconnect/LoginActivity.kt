package com.example.a49erconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {


    private lateinit var btn_signup: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btn_signup = findViewById(R.id.btn_signup)
        btn_signup.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)

            startActivity(intent)
        }
    }
}