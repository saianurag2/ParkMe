package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button submit_button2 = (Button) findViewById(R.id.submit2);

        submit_button2.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submit2Intent = new Intent(LoginActivity.this, ProfileActivity.class);

                startActivity(submit2Intent);
            }
        }));

    }
}
