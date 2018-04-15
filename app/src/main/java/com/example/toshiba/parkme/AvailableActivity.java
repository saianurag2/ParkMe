package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
    }
}
