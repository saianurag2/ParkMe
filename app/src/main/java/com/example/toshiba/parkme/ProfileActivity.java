package com.example.toshiba.parkme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    private Button list_button;
    private Button signout_button;
    private TextView Thename;


    //firebase
   FirebaseUser Current_user;
   DatabaseReference databaseReference1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Thename = (TextView) findViewById(R.id.nameid);
        Current_user = FirebaseAuth.getInstance().getCurrentUser();
        if(Current_user != null) {
            databaseReference1 = FirebaseDatabase.getInstance().getReference();
        }

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
            //itemlist = new ArrayList<>();
                //                //String user_name=dataSnapshot.child(uid).child("name").getValue(String.class);
                //                //User user = dataSnapshot.getValue(User.class);
                //                //Thename.setText(user.setName());
                String name = dataSnapshot.child("Users").child(Current_user.getUid()).child("name").getValue(String.class);
                Thename.setText(name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        list_button = (Button) findViewById(R.id.list);
        signout_button = (Button) findViewById(R.id.logout_button);

        list_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(ProfileActivity.this, BookActivity.class);

                startActivity(listIntent);
            }
        }));

        signout_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log out action
                FirebaseAuth.getInstance().signOut();
                Intent listIntent = new Intent(ProfileActivity.this, MainActivity.class);

                startActivity(listIntent);
            }
        }));

    }

    @Override
    public void onBackPressed()
    {
        FirebaseAuth.getInstance().signOut();
        Intent listIntent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(listIntent);

    }

}