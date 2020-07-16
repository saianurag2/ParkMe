package com.example.toshiba.parkme.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mEmailField: EditText? = null
    private var mPasswordField: EditText? = null
    private var submitButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mEmailField = findViewById(R.id.name_field)
        mPasswordField = findViewById(R.id.password_field)
        submitButton = findViewById(R.id.submit112)
        mAuth = FirebaseAuth.getInstance()
        submitButton!!.setOnClickListener {
            val email = mEmailField!!.text.toString()
            val password = mPasswordField!!.text.toString()
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Fields Are Empty", Toast.LENGTH_LONG).show()
            } else {
                mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this@LoginActivity) {
                    if (!it.isSuccessful) {
                        Toast.makeText(applicationContext, "Email/password incorrect", Toast.LENGTH_LONG).show()
                    } else {
                        startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                        finish() //back button will close the app
                    }
                }
            }
        }
    }
}