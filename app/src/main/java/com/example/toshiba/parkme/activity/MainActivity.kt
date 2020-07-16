package com.example.toshiba.parkme.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.R


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 : Button = findViewById(R.id.signup)
        button1.setOnClickListener {
            val button1Intent = Intent(this@MainActivity, SignUpActivity::class.java)
            startActivity(button1Intent)
            finish()
        }
        val button2 : Button = findViewById(R.id.login)
        button2.setOnClickListener {
            val button2Intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(button2Intent)
            finish()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}

