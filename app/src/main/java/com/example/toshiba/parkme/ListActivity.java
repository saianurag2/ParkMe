package com.example.toshiba.parkme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();

        final int ftime = intent.getIntExtra("fromtime",0);
        final int ttime = intent.getIntExtra("totime",0);
        final int f1,f2,t1,t2;
        f1=ftime;
        f2=ftime;
        t1=ttime;
        t2=ttime;

//        Toast.makeText(getApplicationContext(),"F   "+ftime+"T   "+ttime,Toast.LENGTH_LONG).show();


        //Toast.makeText(getApplicationContext(),"into list",Toast.LENGTH_SHORT).show();
        TextView south_avenue_mall = (TextView) findViewById(R.id.south_avenue_mall);

        // Set a click listener on that View
        south_avenue_mall.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.

            @Override
            public void onClick(View view) {

                Intent south_avenue_mallIntent = new Intent(ListActivity.this, SAMAvailable.class);

                south_avenue_mallIntent.putExtra("fromtime", ftime);
                south_avenue_mallIntent.putExtra("totime", ttime);


                // Start the new activity
                startActivity(south_avenue_mallIntent);
            }
        });


        final TextView samdareeya = (TextView) findViewById(R.id.samdareeya);

        // Set a click listener on that View
        samdareeya.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent samdareeyaIntent = new Intent(ListActivity.this, SdAvailable.class);
                samdareeyaIntent.putExtra("fromtime",f1);
                samdareeyaIntent.putExtra("totime",t1);


                // Start the new activity
                startActivity(samdareeyaIntent);
            }
        });

        TextView pvr_cinemas = (TextView) findViewById(R.id.pvr_cinemas);

        // Set a click listener on that View
        pvr_cinemas.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent pvr_cinemasIntent = new Intent(ListActivity.this, RCAvailable.class);
                pvr_cinemasIntent.putExtra("fromtime",f2);
                pvr_cinemasIntent.putExtra("totime",t2);



                // Start the new activity
                startActivity(pvr_cinemasIntent);
            }
        });

    }
}
