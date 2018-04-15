package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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

public class SdAvailable extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference rootdatabase = firebaseDatabase.getReference();
    private DatabaseReference childreference = rootdatabase.child("Parking Area").child("Sd");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);

        Intent intent = getIntent();
        final int ftime = intent.getIntExtra("fromtime",0);
        final int ttime = intent.getIntExtra("totime",0);

        Button back_button = (Button) findViewById(R.id.back);

        final TextView Sd = (TextView) findViewById(R.id.availability_status);

        childreference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             /*   for(DataSnapshot ds : dataSnapshot.getChildren()){
                    int l1slot1= ds.child("lhtc1").child("slot1").getValue(Integer.class);
                    int l1slot2= ds.child("lhtc1").child("slot2").getValue(Integer.class);
                    int l2slot1= ds.child("lhtc2").child("slot1").getValue(Integer.class);  */

                int sda = dataSnapshot.child("Available").getValue(Integer.class);
                Log.d("TAG", "onvaluereceival: "+  sda);
                Sd.setText(Integer.toString(sda)+" Slots");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("TAG", "Failed to read value.");
                //   Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();


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

                        //receive data from book activity for TO


                        //from and to data in firebase to be changed to current hour+1 to to_value_from_book_activity
                        //Also decrement the value at parkingarea -> SAM -> Available
                        //Also increment value at parkingrea -> SAM -> booked

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Toast.makeText(getApplicationContext(),"Your slot is booked! ", Toast.LENGTH_LONG).show();

            }
        });

        Button back1 = (Button) findViewById(R.id.back1);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(SdAvailable.this, ListActivity.class);
                startActivity(back);

            }
        });

        back_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SdAvailable.this, ProfileActivity.class);

                startActivity(backIntent);
            }
        }));
    }
}
