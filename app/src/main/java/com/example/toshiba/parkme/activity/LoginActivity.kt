package com.example.toshiba.parkme.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.Constants
import com.example.toshiba.parkme.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var mEmailField: EditText? = null
    private var mPasswordField: EditText? = null
    private var submitButton: Button? = null
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mEmailField = findViewById(R.id.name_field)
        mPasswordField = findViewById(R.id.password_field)
        submitButton = findViewById(R.id.submit112)
        mAuth = FirebaseAuth.getInstance()
        sharedPreferences = applicationContext.getSharedPreferences("loginDetail", Context.MODE_PRIVATE)
        val progressDialog : ProgressBar = findViewById(R.id.progressbar)
        progressDialog.visibility = View.GONE
        submitButton!!.setOnClickListener {
            val email = mEmailField!!.text.toString()
            val password = mPasswordField!!.text.toString()
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Fields Are Empty", Toast.LENGTH_LONG).show()
            } else {
                progressDialog.visibility = View.VISIBLE
                mAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this@LoginActivity) {
                    progressDialog.visibility = View.GONE
                    if (!it.isSuccessful) {
                        Toast.makeText(applicationContext, "Email/password incorrect", Toast.LENGTH_LONG).show()
                    } else {
                        val editor = sharedPreferences!!.edit()
                        editor.putString(Constants.userLoginStatus, Constants.isLoggedIn)
                        editor.putString(Constants.email1, email)
                        editor.putString(Constants.password1, password)
                        editor.apply()
                        startActivity(Intent(this@LoginActivity, ProfileActivity::class.java))
                        finish()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}