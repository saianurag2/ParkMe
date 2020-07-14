package com.example.toshiba.parkme.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.toshiba.parkme.R;

public class AvailableActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);

        Button back_button = (Button) findViewById(R.id.back);

        back_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(AvailableActivity.this, ProfileActivity.class);

                startActivity(backIntent);
            }
        }));

        Button back1 = (Button) findViewById(R.id.back1);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(AvailableActivity.this, ListActivity.class);
            }
        });
    }
}
