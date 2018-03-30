package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        TextView south_avenue_mall = (TextView) findViewById(R.id.south_avenue_mall);

        // Set a click listener on that View
        south_avenue_mall.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent south_avenue_mallIntent = new Intent(ListActivity.this, BookActivity.class);

                // Start the new activity
                startActivity(south_avenue_mallIntent);
            }
        });


        TextView samdareeya = (TextView) findViewById(R.id.samdareeya);

        // Set a click listener on that View
        samdareeya.setOnClickListener(new OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent samdareeyaIntent = new Intent(ListActivity.this, BookActivity.class);

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
                Intent pvr_cinemasIntent = new Intent(ListActivity.this, BookActivity.class);

                // Start the new activity
                startActivity(pvr_cinemasIntent);
            }
        });

    }
}
