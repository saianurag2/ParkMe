package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.signup);

        button1.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent button1Intent = new Intent(MainActivity.this, SignupActivity.class);

                startActivity(button1Intent);
            }
        }));

        Button button2 = (Button) findViewById(R.id.login);

        button2.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent button2Intent = new Intent(MainActivity.this, LoginActivity.class);

                startActivity(button2Intent);
            }
        }));
    }
}





