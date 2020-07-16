package com.example.toshiba.parkme.activity

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.Constants
import com.example.toshiba.parkme.Constants.loginSharedPref
import com.example.toshiba.parkme.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*


class ProfileActivity : AppCompatActivity() {

    private var list_button: Button? = null
    private var signout_button: Button? = null
    private var status: Button? = null
    private var thename: TextView? = null
    private var progressDialog: ProgressBar? = null

    private var currentUser: FirebaseUser? = null
    var databaseReference: DatabaseReference? = null
    var from = 0
    var to = 0
    var loc = 0
    var avv = 0
    var bkk = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        thename = findViewById(R.id.nameid)
        currentUser = FirebaseAuth.getInstance().currentUser
        databaseReference = FirebaseDatabase.getInstance().reference
        progressDialog = findViewById(R.id.progressbar)
        list_button = findViewById<View>(R.id.list) as Button
        signout_button = findViewById<View>(R.id.logout_button) as Button
        status = findViewById<View>(R.id.statusbutton) as Button
        list_button!!.setOnClickListener {
            val listIntent = Intent(this@ProfileActivity, BookActivity::class.java)
            if (!(from == 0 && to == 0)) {
                Toast.makeText(applicationContext, "ALREADY BOOKED! CHECK STATUS", Toast.LENGTH_LONG).show()
            } else {
                startActivity(listIntent)
            }
        }
        status!!.setOnClickListener {
            val intent = Intent(this@ProfileActivity, StatusActivity::class.java)
            startActivity(intent)
        }
        signout_button!!.setOnClickListener { //Log out action
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("If you log out, your booking will be cancelled, if any. Are you sure you want to proceed?").setTitle("WARNING!")
                    .setCancelable(false)
                    .setPositiveButton("Yes, Log me out", DialogInterface.OnClickListener { _, _ ->
                        FirebaseAuth.getInstance().signOut()
                        val prefs = getSharedPreferences(loginSharedPref, Context.MODE_PRIVATE)
                        val edit: Editor = prefs.edit()
                        edit.clear()
                        edit.putString(Constants.userLoginStatus, Constants.isNotLoggedIn)
                        edit.apply()
                        resetParams()
                        finish()
                        Toast.makeText(applicationContext, "SIGNED OUT", Toast.LENGTH_LONG).show()
                        val listIntent = Intent(this@ProfileActivity, MainActivity::class.java)
                        startActivity(listIntent)
                    })
                    .setNegativeButton("No, I'll stay") { dialog, _ ->
                        dialog.cancel()
                    }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.show()
        }
    }

    override fun onBackPressed() {
        FirebaseAuth.getInstance().signOut()
        finish()
    }

    override fun onResume() {
        super.onResume()
        databaseReference!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var name = dataSnapshot.child("Users").child(currentUser!!.uid).child("name").getValue(String::class.java)!!
                name = name.toUpperCase(Locale.getDefault())
                thename!!.text = name
                progressDialog?.visibility = View.GONE
                from = dataSnapshot.child("Users").child(currentUser!!.uid).child("from").getValue(Int::class.java)!!
                to = dataSnapshot.child("Users").child(currentUser!!.uid).child("to").getValue(Int::class.java)!!
                loc = dataSnapshot.child("Users").child(currentUser!!.uid).child("loc").getValue(Int::class.java)!!
                if (loc == 1) {
                    avv = dataSnapshot.child("Parking Area").child("SAM").child("Available").getValue(Int::class.java)!!
                    bkk = dataSnapshot.child("Parking Area").child("SAM").child("Booked").getValue(Int::class.java)!!
                } else if (loc == 2) {
                    avv = dataSnapshot.child("Parking Area").child("Sd").child("Available").getValue(Int::class.java)!!
                    bkk = dataSnapshot.child("Parking Area").child("Sd").child("Booked").getValue(Int::class.java)!!
                } else if (loc == 3) {
                    avv = dataSnapshot.child("Parking Area").child("RC").child("Available").getValue(Int::class.java)!!
                    bkk = dataSnapshot.child("Parking Area").child("RC").child("Booked").getValue(Int::class.java)!!
                }

                updateStatus()

            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressDialog?.visibility = View.GONE
                //error occurred
            }
        })
    }

    private fun resetParams() {
        when (loc) {
            1 -> {
                databaseReference!!.child("Users").child(currentUser!!.uid).child("from").setValue(0)
                databaseReference!!.child("Users").child(currentUser!!.uid).child("to").setValue(0)
                databaseReference!!.child("Users").child(currentUser!!.uid).child("loc").setValue(0)
                databaseReference!!.child("Parking Area").child("SAM").child("Available").setValue(avv + 1)
                databaseReference!!.child("Parking Area").child("SAM").child("Booked").setValue(bkk - 1)
            }
            2 -> {
                databaseReference!!.child("Users").child(currentUser!!.uid).child("from").setValue(0)
                databaseReference!!.child("Users").child(currentUser!!.uid).child("to").setValue(0)
                databaseReference!!.child("Users").child(currentUser!!.uid).child("loc").setValue(0)
                databaseReference!!.child("Parking Area").child("RC").child("Available").setValue(avv + 1)
                databaseReference!!.child("Parking Area").child("RC").child("Booked").setValue(bkk - 1)
            }
            3 -> {
                databaseReference!!.child("Users").child(currentUser!!.uid).child("from").setValue(0)
                databaseReference!!.child("Users").child(currentUser!!.uid).child("to").setValue(0)
                databaseReference!!.child("Users").child(currentUser!!.uid).child("loc").setValue(0)
                databaseReference!!.child("Parking Area").child("Sd").child("Available").setValue(avv + 1)
                databaseReference!!.child("Parking Area").child("Sd").child("Booked").setValue(bkk - 1)
            }
        }
    }

    private fun updateStatus() {
        // for a commercial model, the available and booked numbers for each Parking Area should be updated in backend
        // this method (runs once every time Activity is opened) will update the user values, although it should also be handled in backend
        //TODO store to, avv, bkk in sharedPref
        val cal = Calendar.getInstance()
        val hourOfDay = cal[Calendar.HOUR_OF_DAY]
        if (to <= hourOfDay && to != 0) {
            resetParams()
        }
    }
}