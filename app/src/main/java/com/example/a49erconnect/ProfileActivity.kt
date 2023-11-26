package com.example.a49erconnect

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*






class ProfileActivity : AppCompatActivity() {

    private lateinit var userName: String
    private lateinit var userEmail: String
    private lateinit var userUNCCNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)



        // Fetch user details from Firebase
        val currentUser = FirebaseAuth.getInstance().currentUser
        val uid = currentUser?.uid
        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("user").child(
            uid!!
        )

        supportActionBar?.title = "My Profile"

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    userName = snapshot.child("name").value.toString()
                    userEmail = snapshot.child("email").value.toString()
                    userUNCCNumber = snapshot.child("uncc_number").value.toString()

                    Log.d("ProfileActivity", "Name: $userName, Email: $userEmail, UNCC Number: $userUNCCNumber")

                    // Now initialize views and set their content after fetching data
                    initializeViews()
                } else {
                    Log.e("ProfileActivity", "Snapshot does not exist")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("ProfileActivity", "Error fetching data: ${error.message}")
            }
        })
    }

    private fun initializeViews() {
        // Find the TextViews by their IDs after setContentView is called
        val profileNameTextView: TextView = findViewById(R.id.profileNameTextView)
        val profileEmailTextView: TextView = findViewById(R.id.profileEmailTextView)
        val profileUNCCNumberTextView: TextView = findViewById(R.id.profileUNCCNumberTextView)

        // Set the text for the TextViews
        profileNameTextView.text = "Name: $userName"
        profileEmailTextView.text = "Email: $userEmail"
        profileUNCCNumberTextView.text = "UNCC Number: $userUNCCNumber"
    }
}
