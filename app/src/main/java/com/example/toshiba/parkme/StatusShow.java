package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StatusShow extends AppCompatActivity {

   private FirebaseUser currentuser;
   private DatabaseReference databaseUsers;
//    //  ListView listViewUsers;
//    //  List<User> userlist;
//
    private TextView xname ;
    private TextView xemail ;
    private TextView xphone;
    private TextView xfrom;
    private TextView xtoo,loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.status_layout);

         xname  = (TextView) findViewById(R.id.name1);
         xemail = (TextView) findViewById(R.id.email1);
         xphone = (TextView) findViewById(R.id.phonenumber1);
        xfrom = (TextView) findViewById(R.id.bookedfrom);
         xtoo = (TextView) findViewById(R.id.bookedto);
         loc = (TextView) findViewById(R.id.location1);

         currentuser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentuser != null) {
            databaseUsers = FirebaseDatabase.getInstance().getReference();
        }




//
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//
  //              Toast.makeText(getApplicationContext(),"Selected ",Toast.LENGTH_LONG).show();
                String Namez =dataSnapshot.child("Users").child(currentuser.getUid()).child("name").getValue(String.class);
                String Emailz =dataSnapshot.child("Users").child(currentuser.getUid()).child("email").getValue(String.class);
                String Phonez =dataSnapshot.child("Users").child(currentuser.getUid()).child("phone_number").getValue(String.class);
                int froz = dataSnapshot.child("Users").child(currentuser.getUid()).child("from").getValue(Integer.class);
                int toz = dataSnapshot.child("Users").child(currentuser.getUid()).child("to").getValue(Integer.class);
                int locz = dataSnapshot.child("Users").child(currentuser.getUid()).child("loc").getValue(Integer.class);
               String fr = Integer.toString(froz);
                String tr = Integer.toString(toz);
                xname.setText(Namez);
                xemail.setText(Emailz);
                xphone.setText(Phonez);
                if(froz==0 && toz==0){
                    xfrom.setText("No Booking");
                    xtoo.setText("No Booking");
                }else {
                    if(locz==1) {
                        loc.setText("SAM");
                        xfrom.setText(fr + "Hrs");
                        xtoo.setText(tr + "Hrs");
                    }else if(locz==1) {
                        loc.setText("Samdareeya");
                        xfrom.setText(fr + "Hrs");
                        xtoo.setText(tr + "Hrs");
                    }else if(locz==1) {
                        loc.setText("Sadar");
                        xfrom.setText(fr + "Hrs");
                        xtoo.setText(tr + "Hrs");
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        //  listViewUsers = (ListView) findViewById(R.id.listviewUser);
//        //  userlist = new ArrayList<>();
//

        Button button2 = (Button) findViewById(R.id.backx);

        button2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent button2Intent = new Intent(StatusShow.this, ProfileActivity.class);

                startActivity(button2Intent);
            }
        }));

        Button buttonb = (Button) findViewById(R.id.bookagain);

        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(getApplicationContext(),"here",Toast.LENGTH_SHORT).show();
                Intent button = new Intent(StatusShow.this,bookagain.class);
                startActivity(button);
            }
        });
    }
}

//    @Override
//    protected void onStart() {
//        super.onStart();
//        databaseUsers.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot usersnapshot : dataSnapshot.getChildren()) {
//                    User user = usersnapshot.getValue(User.class);
//
//                    //userlist.add(user);
//                }
//                statusActivity adapter = new statusActivity(StatusShow.this,userlist);
//                listViewUsers.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//}


