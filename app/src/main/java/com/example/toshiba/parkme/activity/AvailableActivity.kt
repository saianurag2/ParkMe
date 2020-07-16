package com.example.toshiba.parkme.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.R

class AvailableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available)
        val backButton : Button = findViewById(R.id.back)
        backButton.setOnClickListener {
            val backIntent = Intent(this@AvailableActivity, ProfileActivity::class.java)
            startActivity(backIntent)
        }
        val back1 : Button= findViewById(R.id.back1)
        back1.setOnClickListener {
            val back = Intent(this@AvailableActivity, ListActivity::class.java)
            startActivity(back)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}