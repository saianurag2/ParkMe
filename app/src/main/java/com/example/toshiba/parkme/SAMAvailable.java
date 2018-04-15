package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SAMAvailable extends AppCompatActivity {

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference rootdatabase = firebaseDatabase.getReference();
    private DatabaseReference childreference = rootdatabase.child("Parking Area");

    public TextView SAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);



        SAM = (TextView) findViewById(R.id.availability_status);

        childreference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sama = dataSnapshot.child("SAM").getValue(Integer.class);
                Log.d("TAG", "onvaluereceival: "+ sama);
                SAM.setText(Integer.toString(sama)+" Slots");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("TAG", "Failed to read value.");
                //   Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();


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
    }
}
