package com.example.toshiba.parkme.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.R
import java.util.*

class BookActivity : AppCompatActivity() {
    private var FromTime: TextView? = null
    private var to_view: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        //FROM TIME
        FromTime = findViewById<View>(R.id.fromtime) as TextView

        //get time
        val cal = Calendar.getInstance()
        val minute = cal[Calendar.MINUTE]
        //12 hour format
        //int hour = cal.get(Calendar.HOUR);
        //24 hour format
        val hourofday = cal[Calendar.HOUR_OF_DAY]
        FromTime!!.text = Integer.toString(hourofday + 1) + "Hrs "

        //TO TIME
        to_view = findViewById<View>(R.id.editTo) as EditText
        val check_button = findViewById<View>(R.id.check) as Button
        check_button.setOnClickListener {
            if (hourofday > 22)
            {
                Toast.makeText(applicationContext, "BOOKING CLOSED NOW. TRY_LATER", Toast.LENGTH_LONG).show()
            } else {
                val checkIntent = Intent(this@BookActivity, ListActivity::class.java)
                val data = to_view!!.text.toString()
                val endTime = data.toInt()
                if (endTime + hourofday + 1 <= 24) {
                    checkIntent.putExtra("fromtime", hourofday + 1)
                    checkIntent.putExtra("totime", endTime + hourofday + 1)
                    startActivity(checkIntent)
                } else {
                    Toast.makeText(applicationContext, "CANNOT BOOK FOR NEXT DAY", Toast.LENGTH_LONG).show()
                }
            }
        }
        val back_button = findViewById<View>(R.id.backbook) as Button
        back_button.setOnClickListener {
            val backIntent = Intent(this@BookActivity, ProfileActivity::class.java)
            startActivity(backIntent)
        }
    }
}