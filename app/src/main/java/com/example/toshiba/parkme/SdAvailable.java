package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

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

        Button back_button = (Button) findViewById(R.id.back);

        final TextView Sd = (TextView) findViewById(R.id.availability_status);

        childreference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             /*   for(DataSnapshot ds : dataSnapshot.getChildren()){
                    int l1slot1= ds.child("lhtc1").child("slot1").getValue(Integer.class);
                    int l1slot2= ds.child("lhtc1").child("slot2").getValue(Integer.class);
                    int l2slot1= ds.child("lhtc2").child("slot1").getValue(Integer.class);  */

                int sda = dataSnapshot.getValue(Integer.class);
                Log.d("TAG", "onvaluereceival: "+  sda);
                Sd.setText(Integer.toString(sda)+" Slots");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("TAG", "Failed to read value.");
                //   Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();


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
