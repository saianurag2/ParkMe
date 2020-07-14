package com.example.toshiba.parkme.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshiba.parkme.R;
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
    int av,bk;

    FirebaseUser current_user;

    DatabaseReference dref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);


       databaseReference = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        final int ftime = intent.getIntExtra("fromtime",0);
        final int ttime = intent.getIntExtra("totime",0);


//        Toast.makeText(getApplicationContext(), "onCreateBegins", Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),"FROM"+ftime+"TO"+ttime,Toast.LENGTH_LONG).show();


        SAM = (TextView) findViewById(R.id.availability_status);

        current_user = FirebaseAuth.getInstance().getCurrentUser();
        dref = FirebaseDatabase.getInstance().getReference();


        dref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                av = dataSnapshot.child("Parking Area").child("SAM").child("Available").getValue(Integer.class);
                bk = dataSnapshot.child("Parking Area").child("SAM").child("Booked").getValue(Integer.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


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
  //              Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();


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



                if(av==0) {
                    Toast.makeText(getApplicationContext(),"NO SLOTS AVAILABLE FOR BOOKING!", Toast.LENGTH_LONG).show();
                }else {
                    dref.child("Users").child(current_user.getUid()).child("from").setValue(ftime);
                    dref.child("Users").child(current_user.getUid()).child("to").setValue(ttime);
                    dref.child("Users").child(current_user.getUid()).child("loc").setValue(1);
                    dref.child("Parking Area").child("SAM").child("Available").setValue(av - 1);
                    dref.child("Parking Area").child("SAM").child("Booked").setValue(bk + 1);

                    Toast.makeText(getApplicationContext(), "Booking Successful!", Toast.LENGTH_LONG).show();
                    Intent back = new Intent(SAMAvailable.this, ProfileActivity.class);
                    startActivity(back);


                }
            }
        });


    }


}
