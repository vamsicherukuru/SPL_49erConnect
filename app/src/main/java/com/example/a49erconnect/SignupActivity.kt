package com.example.a49erconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {

    private lateinit var uncc_email: EditText;
    private lateinit var uncc_name: EditText;
    private lateinit var uncc_number: EditText;
    private lateinit var uncc_password: EditText;
    private lateinit var btn_signup: Button;
    private lateinit var btn_login: Button;


    //    firebase auth
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()



        mAuth = FirebaseAuth.getInstance()


        uncc_email = findViewById(R.id.uncc_email)
        uncc_name = findViewById(R.id.uncc_name)
        uncc_number = findViewById(R.id.uncc_number)
        uncc_password = findViewById(R.id.uncc_password)
        btn_login = findViewById(R.id.btn_login)
        btn_signup = findViewById(R.id.btn_signup)



        btn_signup.setOnClickListener {
            val email = uncc_email.text.toString()
            val password = uncc_password.text.toString()


            signUp(email,password)
        }

    }


    private fun signUp(email: String, password: String){

        val unccEmailRegex = Regex("^\\S+@uncc\\.edu\$")

        if (!unccEmailRegex.matches(email)) {
            // Email doesn't match the required pattern
            Toast.makeText(this@SignupActivity, "Please make sure to use UNCC Email", Toast.LENGTH_SHORT).show()
            return
        }





//        logic of creating user


        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    //code for jumping to homeactivity

                    val intent = Intent(this@SignupActivity, MainActivity::class.java)
                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignupActivity,"Some Error Occurred",Toast.LENGTH_SHORT).show()
                }
            }



    }

}