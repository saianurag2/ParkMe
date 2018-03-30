package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button list_button = (Button) findViewById(R.id.list);

        list_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(ProfileActivity.this, ListActivity.class);

                startActivity(listIntent);
            }
        }));

    }
}
