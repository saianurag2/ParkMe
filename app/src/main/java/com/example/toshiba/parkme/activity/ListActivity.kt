package com.example.toshiba.parkme.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.toshiba.parkme.R

class ListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val intent = intent
        val ftime = intent.getIntExtra("fromtime", 0)
        val ttime = intent.getIntExtra("totime", 0)
        val f1: Int
        val f2: Int
        val t1: Int
        val t2: Int
        f1 = ftime
        f2 = ftime
        t1 = ttime
        t2 = ttime
        val south_avenue_mall = findViewById<View>(R.id.south_avenue_mall) as TextView
        south_avenue_mall.setOnClickListener {
            val south_avenue_mallIntent = Intent(this@ListActivity, SAMAvailableActivity::class.java)
            south_avenue_mallIntent.putExtra("fromtime", ftime)
            south_avenue_mallIntent.putExtra("totime", ttime)
            startActivity(south_avenue_mallIntent)
        }
        val samdareeya = findViewById<View>(R.id.samdareeya) as TextView
        samdareeya.setOnClickListener {
            val samdareeyaIntent = Intent(this@ListActivity, SdAvailableActivity::class.java)
            samdareeyaIntent.putExtra("fromtime", f1)
            samdareeyaIntent.putExtra("totime", t1)
            startActivity(samdareeyaIntent)
        }
        val pvr_cinemas = findViewById<View>(R.id.pvr_cinemas) as TextView
        pvr_cinemas.setOnClickListener {
            val pvr_cinemasIntent = Intent(this@ListActivity, RCAvailableActivity::class.java)
            pvr_cinemasIntent.putExtra("fromtime", f2)
            pvr_cinemasIntent.putExtra("totime", t2)
            startActivity(pvr_cinemasIntent)
        }
    }
}