package com.example.toshiba.parkme.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshiba.parkme.R;

import java.util.Calendar;

public class BookActivity extends AppCompatActivity  {

    private TextView FromTime;
    private EditText to_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);


        //FROM TIME

        FromTime = (TextView) findViewById(R.id.fromtime);


        //get time
        Calendar cal = Calendar.getInstance();
        final int minute = cal.get(Calendar.MINUTE);
        //12 hour format
        //int hour = cal.get(Calendar.HOUR);
        //24 hour format
        final int hourofday = cal.get(Calendar.HOUR_OF_DAY);

        FromTime.setText(Integer.toString(hourofday + 1) + "Hrs ");


        //TO TIME

         to_view = (EditText) findViewById(R.id.editTo);






        Button check_button = (Button) findViewById(R.id.check);

        check_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {


                if(hourofday>22)   //TODO 22
                {
                    Toast.makeText(getApplicationContext(),"BOOKING CLOSED NOW. TRY_LATER",Toast.LENGTH_LONG).show();
                }else {
                    Intent checkIntent = new Intent(BookActivity.this, ListActivity.class);

                    String data = to_view.getText().toString();
                    final int end_time = Integer.parseInt(data);
                    int Num;
                    try {
                        Num = Integer.parseInt(data);
                    } catch (NumberFormatException nfe) {
                        System.out.println("Could not parse " + nfe);
                    }

                  //  Toast.makeText(getApplicationContext(), "End Time " + end_time, Toast.LENGTH_LONG).show();

                    if ((end_time + hourofday + 1) <= 24) {   //TODO 24
                        //pass data
//                        if((end_time+hourofday+1)==24)
//                        {
//
//                        }
                        checkIntent.putExtra("fromtime", hourofday + 1);
                        // Log.d("TAG","end_time value"+end_time);
                        checkIntent.putExtra("totime", end_time + hourofday + 1);

//                      Toast.makeText(getApplicationContext(),"fromvalueis"+hourofday+1+"tovalueis"+end_time,Toast.LENGTH_LONG).show();
                        startActivity(checkIntent);
                    } else {
                        Toast.makeText(getApplicationContext(), "CANNOT BOOK FOR NEXT DAY", Toast.LENGTH_LONG).show();
                    }
                }

            }
       }));

        Button back_button = (Button) findViewById(R.id.backbook);

        back_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(BookActivity.this, ProfileActivity.class);
                startActivity(backIntent);
            }
        }));

        //Performing action onItemSelected and onNothing selected


    }



}