package com.example.toshiba.parkme.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class StatusActivity : AppCompatActivity() {
    private var currentuser: FirebaseUser? = null
    private var databaseUsers: DatabaseReference? = null
    private var xname: TextView? = null
    private var xemail: TextView? = null
    private var xphone: TextView? = null
    private var xfrom: TextView? = null
    private var xtoo: TextView? = null
    private var tvLoc: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.status_layout)
        xname = findViewById<View>(R.id.name1) as TextView
        xemail = findViewById<View>(R.id.email1) as TextView
        xphone = findViewById<View>(R.id.phonenumber1) as TextView
        xfrom = findViewById<View>(R.id.bookedfrom) as TextView
        xtoo = findViewById<View>(R.id.bookedto) as TextView
        tvLoc = findViewById<View>(R.id.location1) as TextView
        currentuser = FirebaseAuth.getInstance().currentUser
        if (currentuser != null) {
            databaseUsers = FirebaseDatabase.getInstance().reference
        }
        val progressDialog : ProgressBar = findViewById(R.id.progressbar)
        val progressDialog2 : ProgressBar = findViewById(R.id.progressbar2)
        databaseUsers?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                progressDialog.visibility = View.GONE
                progressDialog2.visibility = View.GONE
                val name = dataSnapshot.child("Users").child(currentuser!!.uid).child("name").getValue(String::class.java) ?: "NA"
                val email = dataSnapshot.child("Users").child(currentuser!!.uid).child("email").getValue(String::class.java) ?: "NA"
                val phone = dataSnapshot.child("Users").child(currentuser!!.uid).child("phone_number").getValue(String::class.java) ?: "NA"
                val from = dataSnapshot.child("Users").child(currentuser!!.uid).child("from").getValue(Int::class.java) ?: 0
                val to = dataSnapshot.child("Users").child(currentuser!!.uid).child("to").getValue(Int::class.java) ?: 0
                val loc = dataSnapshot.child("Users").child(currentuser!!.uid).child("loc").getValue(Int::class.java) ?: 0
                xname!!.text = name
                xemail!!.text = email
                xphone!!.text = phone
                if (from == 0 && to == 0) {
                    tvLoc!!.visibility = View.INVISIBLE
                    xfrom!!.text = getString(R.string.noBooking)
                    xtoo!!.text = getString(R.string.noBooking)
                } else {
                    when (loc) {
                        1 -> {
                            tvLoc!!.text = "SAM"
                            xfrom!!.text = "$from" + "Hrs"
                            xtoo!!.text = """${to}Hrs"""
                        }
                        2 -> {
                            tvLoc!!.text = "Samdareeya"
                            xfrom!!.text = """${from}Hrs"""
                            xtoo!!.text = """${to}Hrs"""
                        }
                        3 -> {
                            tvLoc!!.text = "Sadar"
                            xfrom!!.text = """${from}Hrs"""
                            xtoo!!.text = "${to}Hrs"
                        }
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressDialog.visibility = View.GONE
                progressDialog2.visibility = View.GONE
            }
        })


        val button2 = findViewById<View>(R.id.backx) as Button
        button2.setOnClickListener {
            val button2Intent = Intent(this, ProfileActivity::class.java)
            startActivity(button2Intent)
        }
        val button3 = findViewById<View>(R.id.bookagain) as Button
        button3.setOnClickListener {
            val button = Intent(this, BookAgainActivity::class.java)
            startActivity(button)
        }
    }
}