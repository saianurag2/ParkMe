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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class ProfileActivity extends AppCompatActivity {
//
//    ArrayAdapter<String> adapter;
    private Button list_button;
    private Button signout_button,status;
    private TextView Thename;


    //firebase
   FirebaseUser Current_user;
   DatabaseReference databaseReference1,dref1;

   int f,t,loc;
   int avv,bkk;

   //loc variable 1 ==SAM   ;2==Sd 3== RC

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Thename = (TextView) findViewById(R.id.nameid);
        Current_user = FirebaseAuth.getInstance().getCurrentUser();
        // if(Current_user != null) {
        databaseReference1 = FirebaseDatabase.getInstance().getReference();
        // }
 //       Toast.makeText(getApplicationContext(),"current"+Current_user.getUid(),Toast.LENGTH_LONG).show();

        dref1 = FirebaseDatabase.getInstance().getReference();

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //itemlist = new ArrayList<>();
                //                //String user_name=dataSnapshot.child(uid).child("name").getValue(String.class);
                //                //User user = dataSnapshot.getValue(User.class);
                //                //Thename.setText(user.setName());
                String name = dataSnapshot.child("Users").child(Current_user.getUid()).child("name").getValue(String.class);
                name= name.toUpperCase();
                Thename.setText(name);
                f =dataSnapshot.child("Users").child(Current_user.getUid()).child("from").getValue(Integer.class);
                t =dataSnapshot.child("Users").child(Current_user.getUid()).child("to").getValue(Integer.class);
                loc = dataSnapshot.child("Users").child(Current_user.getUid()).child("loc").getValue(Integer.class);
                if(loc == 1)
                {
                    avv = dataSnapshot.child("Parking Area").child("SAM").child("Available").getValue(Integer.class);
                    bkk = dataSnapshot.child("Parking Area").child("SAM").child("Booked").getValue(Integer.class);
                }else if (loc ==2)
                {
                    avv = dataSnapshot.child("Parking Area").child("Sd").child("Available").getValue(Integer.class);
                    bkk = dataSnapshot.child("Parking Area").child("Sd").child("Booked").getValue(Integer.class);
                } else if (loc ==3)
                {
                    avv = dataSnapshot.child("Parking Area").child("RC").child("Available").getValue(Integer.class);
                    bkk = dataSnapshot.child("Parking Area").child("RC").child("Booked").getValue(Integer.class);
                }
                //           Toast.makeText(getApplicationContext(),"Fr Too"+f+t,Toast.LENGTH_LONG).show();

                // f = Integer.parseInt(fr);
                // t = Integer.parseInt(too);

                //hourly refresh
                Calendar cal = Calendar.getInstance();
                //final int minute = cal.get(Calendar.MINUTE);
                //12 hour format
                //int hour = cal.get(Calendar.HOUR);
                //24 hour format
                final int hourofday = cal.get(Calendar.HOUR_OF_DAY);
                if(t <=hourofday && (t!=0)) {

                    if (loc == 1) {
                        dref1.child("Users").child(Current_user.getUid()).child("from").setValue(0);
                        dref1.child("Users").child(Current_user.getUid()).child("to").setValue(0);
                        dref1.child("Users").child(Current_user.getUid()).child("loc").setValue(0);
                        dref1.child("Parking Area").child("SAM").child("Available").setValue(avv + 1);
                        dref1.child("Parking Area").child("SAM").child("Booked").setValue(bkk - 1);
                    } else if (loc == 2) {
                        dref1.child("Users").child(Current_user.getUid()).child("from").setValue(0);
                        dref1.child("Users").child(Current_user.getUid()).child("to").setValue(0);
                        dref1.child("Users").child(Current_user.getUid()).child("loc").setValue(0);
                        dref1.child("Parking Area").child("Sd").child("Available").setValue(avv + 1);
                        dref1.child("Parking Area").child("Sd").child("Booked").setValue(bkk - 1);
                    } else if (loc == 3) {
                        dref1.child("Users").child(Current_user.getUid()).child("from").setValue(0);
                        dref1.child("Users").child(Current_user.getUid()).child("to").setValue(0);
                        dref1.child("Users").child(Current_user.getUid()).child("loc").setValue(0);
                        dref1.child("Parking Area").child("RC").child("Available").setValue(avv + 1);
                        dref1.child("Parking Area").child("RC").child("Booked").setValue(bkk - 1);
                    }

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



//        if(f==-1 && t==-1)
//        {
//            if(loc == 1)
//            {
//
//                dref1.child("Parking Area").child("SAM").child("Available").setValue(avv + 1);
//                dref1.child("Parking Area").child("SAM").child("Booked").setValue(bkk - 1);
//            }else if (loc ==2)
//            {
//                dref1.child("Parking Area").child("SAM").child("Available").setValue(avv + 1);
//                dref1.child("Parking Area").child("SAM").child("Booked").setValue(bkk - 1);
//            } else if (loc ==3) {
//                dref1.child("Parking Area").child("SAM").child("Available").setValue(avv + 1);
//                dref1.child("Parking Area").child("SAM").child("Booked").setValue(bkk - 1);
//            }
//            f=0;
//            t=0;
//        }


        list_button = (Button) findViewById(R.id.list);
        signout_button = (Button) findViewById(R.id.logout_button);
        status = (Button) findViewById(R.id.statusbutton);

        list_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(ProfileActivity.this, BookActivity.class);
                if(!(f==0 && t==0)){
                   Toast.makeText(getApplicationContext(),"ALREADY BOOKED! CHECK STATUS",Toast.LENGTH_LONG).show();
               }else {
                    startActivity(listIntent);
                }
            }
        }));

        status.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(ProfileActivity.this, StatusShow.class);
                startActivity(Intent);
            }
        });
        signout_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {

                //Log out action
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getApplicationContext(),"SIGNED OUT",Toast.LENGTH_LONG).show();
                Intent listIntent = new Intent(ProfileActivity.this, MainActivity.class);

                startActivity(listIntent);
            }
        }));

    }

    @Override
    public void onBackPressed()
    {
        FirebaseAuth.getInstance().signOut();
      //  Intent listIntent = new Intent(ProfileActivity.this, MainActivity.class);
      //  startActivity(listIntent);
        finish();

    }

}