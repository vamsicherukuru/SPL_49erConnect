package com.example.a49erconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupActivity : AppCompatActivity() {

    private lateinit var uncc_email: EditText;
    private lateinit var uncc_name: EditText;
    private lateinit var uncc_number: EditText;
    private lateinit var uncc_password: EditText;
    private lateinit var btn_signup: Button;
//    private lateinit var btn_login: Button;
    //    firebase auth
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        supportActionBar?.hide()



        mAuth = FirebaseAuth.getInstance()


        uncc_email = findViewById(R.id.uncc_email)
        uncc_name = findViewById(R.id.uncc_name)
        uncc_number = findViewById(R.id.uncc_number)
        uncc_password = findViewById(R.id.uncc_password)
//        btn_login = findViewById(R.id.btn_login)
        btn_signup = findViewById(R.id.btn_signup)



        btn_signup.setOnClickListener {

            val uncc_name = uncc_name.text.toString()
            val uncc_number = uncc_number.text.toString()
            val email = uncc_email.text.toString()
            val password = uncc_password.text.toString()


            signUp(uncc_name,uncc_number,email,password)
        }

    }


    private fun signUp(uncc_name: String, uncc_number: String,email: String, password: String){

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

                    addUserToDatabase(uncc_name,uncc_number,email,mAuth.currentUser?.uid!!)


                    //code for jumping to homeactivity

                    val intent = Intent(this@SignupActivity, MainActivity::class.java)

                    finish()

                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignupActivity,"Some Error Occurred",Toast.LENGTH_SHORT).show()
                }
            }



    }





    private fun addUserToDatabase(uncc_name: String,uncc_number: String, email:String, uid: String){


        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(uncc_name,uncc_number,email,uid))

    }


}