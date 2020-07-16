package com.example.toshiba.parkme.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class AreaAvailableActivity : AppCompatActivity() {
    var databaseReference: DatabaseReference? = null
    var area: TextView? = null
    var av = 0
    var bk = 0
    private var currentUser: FirebaseUser? = null
    var dref: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_available)
        databaseReference = FirebaseDatabase.getInstance().reference
        val intent = intent
        val ftime = intent.getIntExtra("fromtime", 0)
        val ttime = intent.getIntExtra("totime", 0)
        val areaName = intent.getStringExtra("Area")
        area = findViewById<View>(R.id.availability_status) as TextView
        currentUser = FirebaseAuth.getInstance().currentUser
        dref = FirebaseDatabase.getInstance().reference
        val progressDialog : ProgressBar = findViewById(R.id.progressbar)
        dref!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                progressDialog.visibility = View.GONE
                if(!areaName.isNullOrEmpty()) {
                    av = dataSnapshot.child("Parking Area").child(areaName).child("Available").getValue(Int::class.java)!!
                    bk = dataSnapshot.child("Parking Area").child(areaName).child("Booked").getValue(Int::class.java)!!
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressDialog.visibility = View.GONE
            }
        })

        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                progressDialog.visibility = View.GONE
                if(!areaName.isNullOrEmpty()) {
                    val sama = dataSnapshot.child("Parking Area").child(areaName).child("Available").getValue(Int::class.java)!!
                    Log.d("TAG", "onvaluereceival: $sama")
                    area!!.text = Integer.toString(sama) + " Slots"
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressDialog.visibility = View.GONE
                Log.e("TAG", "Failed to read value.")
            }
        })
        val backButton = findViewById<View>(R.id.back) as Button
        backButton.setOnClickListener {
            val backIntent = Intent(this, ProfileActivity::class.java)
            startActivity(backIntent)
        }
        val back1 = findViewById<View>(R.id.back1) as Button
        back1.setOnClickListener {
            val back = Intent(this, ListActivity::class.java)
            startActivity(back)
        }
        val bookAndPay = findViewById<View>(R.id.booknpay) as Button
        bookAndPay.setOnClickListener {
            if (av == 0) {
                Toast.makeText(applicationContext, "NO SLOTS AVAILABLE FOR BOOKING!", Toast.LENGTH_LONG).show()
            } else {
                dref!!.child("Users").child(currentUser!!.uid).child("from").setValue(ftime)
                dref!!.child("Users").child(currentUser!!.uid).child("to").setValue(ttime)
                dref!!.child("Users").child(currentUser!!.uid).child("loc").setValue(3)
                if(!areaName.isNullOrEmpty()) {
                    dref!!.child("Parking Area").child(areaName).child("Available").setValue(av - 1)
                    dref!!.child("Parking Area").child(areaName).child("Booked").setValue(bk + 1)
                }
                Toast.makeText(applicationContext, "Booking Successful!", Toast.LENGTH_LONG).show()
                val back = Intent(this, ProfileActivity::class.java)
                startActivity(back)
            }
        }
    }
}