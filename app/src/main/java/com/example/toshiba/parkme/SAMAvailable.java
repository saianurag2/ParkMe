package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SAMAvailable extends AppCompatActivity {

    DatabaseReference databaseReference;

    public TextView SAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);


       databaseReference = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        final int ftime = intent.getIntExtra("fromtime",0);
        final int ttime = intent.getIntExtra("totime",0);

        Toast.makeText(getApplicationContext(), "onCreateBegins", Toast.LENGTH_SHORT).show();

        SAM = (TextView) findViewById(R.id.availability_status);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sama = dataSnapshot.child("Parking Area").child("SAM").child("Available").getValue(Integer.class);
                Log.d("TAG", "onvaluereceival: "+ sama);
                SAM.setText(Integer.toString(sama)+" Slots");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("TAG", "Failed to read value.");
                Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();


            }
        });


        Button back_button = (Button) findViewById(R.id.back);


        back_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SAMAvailable.this, ProfileActivity.class);

                startActivity(backIntent);
            }
        }));

        Button back1 = (Button) findViewById(R.id.back1);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SAMAvailable.this, ListActivity.class);
                startActivity(back);
            }
        });

        Button bookandpay= (Button) findViewById(R.id.booknpay);

        bookandpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseUser current_user;
                DatabaseReference dref;
                current_user = FirebaseAuth.getInstance().getCurrentUser();
                dref = FirebaseDatabase.getInstance().getReference();

                dref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int from = dataSnapshot.child("Users").child(current_user.getUid()).child("from").getValue(Integer.class);
                        int to = dataSnapshot.child("Users").child(current_user.getUid()).child("to").getValue(Integer.class);

                        //from and to data in firebase to be changed to current hour+1 to to_value_from_book_activity
                        //Also decrement the value at parkingarea -> SAM -> Available
                        //Also increment value at parkingrea -> SAM -> booked

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getApplicationContext(),"Booking Successful!", Toast.LENGTH_LONG).show();

            }
        });


    }
}
