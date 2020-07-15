package com.example.toshiba.parkme.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class BookAgainActivity extends AppCompatActivity {

    FirebaseUser currentuser;
    DatabaseReference databaseReference,databaseReference1;
    int fro,tt;

    TextView fromView;
    EditText hours ;
    int hrs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        hours = (EditText) findViewById(R.id.editTo);
        fromView = (TextView) findViewById(R.id.fromtime);
        currentuser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentuser != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             fro = dataSnapshot.child("Users").child(currentuser.getUid()).child("from").getValue(Integer.class);
             tt= dataSnapshot.child("Users").child(currentuser.getUid()).child("to").getValue(Integer.class);
             fromView.setText(Integer.toString(fro));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button book = (Button) findViewById(R.id.check);
        book.setText(R.string.extendBooking);

        databaseReference1 = FirebaseDatabase.getInstance().getReference();

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String h = hours.getText().toString();
                hrs = Integer.parseInt(h);
                if((hrs + tt)<=24) {
                    databaseReference1.child("Users").child(currentuser.getUid()).child("to").setValue(hrs + tt);

                    Toast.makeText(getApplicationContext(), "Booking extended successfully!", Toast.LENGTH_LONG).show();
                    Intent backIntent = new Intent(BookAgainActivity.this, ProfileActivity.class);
                    startActivity(backIntent);
                }else{
                    Toast.makeText(getApplicationContext(), "Booking for next day not available", Toast.LENGTH_LONG).show();
                }


            }
        });

        Button back_button = (Button) findViewById(R.id.backbook);

        back_button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(BookAgainActivity.this, ProfileActivity.class);
                startActivity(backIntent);
            }
        }));


    }
}




