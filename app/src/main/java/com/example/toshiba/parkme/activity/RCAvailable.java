package com.example.toshiba.parkme.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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

public class RCAvailable extends AppCompatActivity {

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
                av = dataSnapshot.child("Parking Area").child("RC").child("Available").getValue(Integer.class);
                bk = dataSnapshot.child("Parking Area").child("RC").child("Booked").getValue(Integer.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int sama = dataSnapshot.child("Parking Area").child("RC").child("Available").getValue(Integer.class);
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
                Intent backIntent = new Intent(RCAvailable.this, ProfileActivity.class);

                startActivity(backIntent);
            }
        }));

        Button back1 = (Button) findViewById(R.id.back1);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back = new Intent(RCAvailable.this, ListActivity.class);
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
                    dref.child("Users").child(current_user.getUid()).child("loc").setValue(3);
                    dref.child("Parking Area").child("RC").child("Available").setValue(av - 1);
                    dref.child("Parking Area").child("RC").child("Booked").setValue(bk + 1);

                    Toast.makeText(getApplicationContext(), "Booking Successful!", Toast.LENGTH_LONG).show();
                    Intent back = new Intent(RCAvailable.this, ProfileActivity.class);
                    startActivity(back);


                }
            }
        });


    }



//    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
//    private DatabaseReference rootdatabase = firebaseDatabase.getReference();
//    private DatabaseReference childreference = rootdatabase.child("Parking Area").child("RC");
//
//    public  TextView RC;
//    int av,bk;
//
//    FirebaseUser current_user;
//    DatabaseReference dref;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_available);
//
//        Intent intent = getIntent();
//        final int ftime = intent.getIntExtra("fromtime",0);
//        final int ttime = intent.getIntExtra("totime",0);
//        //Toast.makeText(getApplicationContext(),"FROM"+ftime+"TO"+ttime,Toast.LENGTH_LONG).show();
//
//        RC = (TextView) findViewById(R.id.availability_status);
//        current_user = FirebaseAuth.getInstance().getCurrentUser();
//        dref = FirebaseDatabase.getInstance().getReference();
//
//        dref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                av = dataSnapshot.child("Parking Area").child("Sd").child("Available").getValue(Integer.class);
//                bk = dataSnapshot.child("Parking Area").child("Sd").child("Booked").getValue(Integer.class);
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//        Button back_button = (Button) findViewById(R.id.back);
//
//        childreference.addValueEventListener(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                int rca= dataSnapshot.child("Available").getValue(Integer.class);
//                Log.d("TAG", "onvaluereceival: "+ rca);
//                RC.setText(Integer.toString(rca)+" Slots");
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//                Log.w("TAG", "Failed to read value.");
//                //   Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
//
//
//            }
//        });
//
//
//
//        Button bookandpay= (Button) findViewById(R.id.booknpay);
//
//        bookandpay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(av==0) {
//                    Toast.makeText(getApplicationContext(),"NO SLOTS AVAILABLE FOR BOOKING!", Toast.LENGTH_LONG).show();
//                }else {
//                    dref.child("Users").child(current_user.getUid()).child("from").setValue(ftime);
//                    dref.child("Users").child(current_user.getUid()).child("to").setValue(ttime);
//                    dref.child("Users").child(current_user.getUid()).child("loc").setValue(3);
//                    dref.child("Parking Area").child("RC").child("Available").setValue(av - 1);
//                    dref.child("Parking Area").child("RC").child("Booked").setValue(bk + 1);
//
//                    Toast.makeText(getApplicationContext(), "Booking Successful!", Toast.LENGTH_LONG).show();
//                    Intent back = new Intent(RCAvailable.this, ProfileActivity.class);
//                    startActivity(back);
//
//
//                }
//
//
////                current_user = FirebaseAuth.getInstance().getCurrentUser();
////                dref = FirebaseDatabase.getInstance().getReference();
////
////                dref.child("Users").child(current_user.getUid()).child("from").setValue(ftime);
////                dref.child("Users").child(current_user.getUid()).child("to").setValue(ttime);
//
////                dref.addValueEventListener(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(DataSnapshot dataSnapshot) {
////                     dataSnapshot.child("Users").child(current_user.getUid()).child("from").setValue(ftime);
////                     //   dataSnapshot.child("Users").child(current_user.getUid()).child("to").getValue(Integer.class);
////
////                        //from and to data in firebase to be changed to current hour+1 to to_value_from_book_activity
////                        //Also decrement the value at parkingarea -> SAM -> Available
////                        //Also increment value at parkingrea -> SAM -> booked
////
////                    }
////
////                    @Override
////                    public void onCancelled(DatabaseError databaseError) {
////
////                    }
////                });
//
//             //   Toast.makeText(getApplicationContext(),"Booking Successful", Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//        back_button.setOnClickListener((new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent backIntent = new Intent(RCAvailable.this, ProfileActivity.class);
//
//                startActivity(backIntent);
//            }
//        }));
//
//        Button back1 = (Button) findViewById(R.id.back1);
//
//        back1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent back = new Intent(RCAvailable.this, ListActivity.class);
//                startActivity(back);
//
//            }
//        });
//
//    }




}





/*package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class AvailableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available);

        Button back_button = (Button) findViewById(R.id.back);

        back_button.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(AvailableActivity.this, ProfileActivity.class);

                startActivity(backIntent);
            }
        }));
    }
}
*/