package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class SignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button submit_button1 = (Button) findViewById(R.id.submit1);

        submit_button1.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submit1Intent = new Intent(SignupActivity.this, ProfileActivity.class);

                startActivity(submit1Intent);
            }
        }));



    }



}
