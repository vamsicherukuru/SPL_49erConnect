package com.example.a49erconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {


    private lateinit var uncc_email: EditText;
    private lateinit var uncc_password: EditText;
    private lateinit var btn_signup: Button;
    private lateinit var btn_login: Button;


//    firebase auth
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()



        uncc_email = findViewById(R.id.uncc_email)
        uncc_password = findViewById(R.id.uncc_password)
        btn_login = findViewById(R.id.btn_login)
        btn_signup = findViewById(R.id.btn_signup)

        btn_signup.setOnClickListener{
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)

        }




//        logging in the user code
        btn_login.setOnClickListener{
            val email = uncc_email.text.toString()
            val password = uncc_password.text.toString()

            login(email,password);
        }

    }



    // other methods

    private fun login(email:String, password:String){
            // logic for logging in user

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@LoginActivity,"User Does Not Exist", Toast.LENGTH_LONG).show()

                }
            }



    }





}