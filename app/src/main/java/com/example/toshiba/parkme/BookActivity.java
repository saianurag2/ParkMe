package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Button check_button = (Button) findViewById(R.id.check);

        check_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent checkIntent = new Intent(BookActivity.this, AvailableActivity.class);

                startActivity(checkIntent);
            }
        }));

    }
}
