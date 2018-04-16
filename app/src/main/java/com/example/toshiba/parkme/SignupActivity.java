package com.example.toshiba.parkme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    private EditText name, email, mobile, password, confpassword;
    private Button signup;
    private String Name, Email, Mobile, Pass, Cpass;


    //firebase
    private FirebaseAuth mAuth;

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        if(mAuth.getCurrentUser() !=null){
//            finish();
//            startActivity(new Intent(SignupActivity.this,ProfileActivity.class));
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = (EditText) findViewById(R.id.name_field);
        email = (EditText) findViewById(R.id.email_field);
        mobile = (EditText) findViewById(R.id.mobile_field);
        password = (EditText) findViewById(R.id.password_field);
        confpassword = (EditText) findViewById(R.id.password_field2);
        signup = (Button) findViewById(R.id.submit1);



        //firebase
        mAuth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isValidate()) {
                    //SignUp();

                    mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                           // Toast.makeText(getApplicationContext(), "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            if(task.isSuccessful()) {
                                final FirebaseUser Fuser = task.getResult().getUser();
                                //Toast.makeText(getApplicationContext(), "fuser made:onComplete:", Toast.LENGTH_SHORT).show();

                                if(Fuser != null ) {
                                    mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if(task.isSuccessful()) {
                                                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                                databaseReference = databaseReference.child(Fuser.getUid());
                                                int bfrom,bto,loc;
                                                bfrom=0;
                                                bto=0;
                                                loc=0;
                                                User userr = new User();
                                                userr.setName(Name);
                                                userr.setEmail(Email);
                                                userr.setPhone_number(Mobile);
                                                userr.setPassword(Pass);
                                                userr.setFrom(bfrom);
                                                userr.setTo(bto);
                                                userr.setLoc(loc);

                                                databaseReference.setValue(userr);

                                            }
                                            else if (!task.isSuccessful()) {
                                                Toast.makeText(getApplicationContext(), "Signin Failure", Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });

                                }
                                Toast.makeText(getApplicationContext(), "Successful!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                            }
                            else {
                                Toast.makeText(getApplicationContext(), "SignUp Error", Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }
            }
        });

    }

    private boolean isValidate() {
        boolean isValidate = true;
        Name = name.getText().toString().trim();
        Email = email.getText().toString().trim();
        Mobile = mobile.getText().toString().trim();
        Pass = password.getText().toString().trim();
        Cpass = confpassword.getText().toString().trim();

        if(Name.length() == 0) {
            name.setError("Enter Name");
            isValidate = false;
        }
        if(Email.length() == 0) {
            name.setError("Enter Email");
            isValidate = false;
        } else if (!Utility.isValidEmail(Email)) {
            email.setError("Please Enter valid Email ID");
            isValidate = false;
        }
        if(Mobile.length() == 0) {
            name.setError("Enter Mobile number");
            isValidate = false;
        }
        if(Pass.length() == 0) {
            password.setError("Enter Password");
            isValidate = false;
        }else if (Pass.length() < 8 ) {
            password.setError("Password must contain Min 8 Char");
            isValidate = false;
        }
        if(Cpass.length() == 0) {
            name.setError("Required");
            isValidate = false;
        } else if (!Cpass.equals(Pass)) {
            confpassword.setError("Passwords Don't match");
            isValidate = false;
        }
         return isValidate;
    }

    private  static class Utility {
        public static boolean isValidEmail(String email) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            CharSequence inputStr = email;
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            return pattern.matcher(inputStr).matches();
        }
    }
}












 /*   private void SignUp() {

        mAuth.createUserWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                Toast.makeText(getApplicationContext(), "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                if(task.isSuccessful()) {
                    final FirebaseUser Fuser = task.getResult().getUser();
                    if(Fuser != null ) {
                        mAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
                                    databaseReference = databaseReference.child(Fuser.getUid());

                                    User userr = new User();
                                    userr.setName(Name);
                                    userr.setEmail(Email);
                                    userr.setPhone_number(Mobile);
                                    userr.setPassword(Pass);

                                    databaseReference.setValue(userr);

                                }
                                else if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }
                    Toast.makeText(SignupActivity.this, "Successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
*/


/*public class SignupActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference users;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText name_field, email_field,mobile_field, password_field;
    private Button  submit_button1;
    String email, password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        //firebase database
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        name_field = (EditText) findViewById(R.id.name_field);
        email_field = (EditText) findViewById(R.id.email_field);
        mobile_field = (EditText) findViewById(R.id.mobile_field);
        password_field = (EditText) findViewById(R.id.password_field);

        //firebase auth
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    //Intent User Account
                    startActivity(new Intent(SignupActivity.this,ProfileActivity.class));
                }

            }
        };

        submit_button1 = (Button) findViewById(R.id.submit1);

        submit_button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final User user = new User(email_field.getText().toString(),
                        password_field.getText().toString(),
                        name_field.getText().toString(),
                        mobile_field.getText().toString());
             users.addListenerForSingleValueEvent(new ValueEventListener() {
                 @Override
                 public void onDataChange(DataSnapshot dataSnapshot) {
                     if(dataSnapshot.child(user.getEmail()).exists())
                         Toast.makeText(SignupActivity.this, "Email already Registered", Toast.LENGTH_SHORT).show();

                     else {
                         email=user.getEmail();
                         password=user.getPassword();
                         users.child(email).setValue(user);
                         Toast.makeText(SignupActivity.this,"Success Register",Toast.LENGTH_SHORT).show();
                         mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this,new OnCompleteListener<AuthResult>() {
                             @Override
                             public void onComplete(@NonNull Task<AuthResult> task) {
                                 if (!task.isSuccessful()) {
                                     Toast.makeText(SignupActivity.this, "Email/password incorrect", Toast.LENGTH_LONG).show();
                                 } else
                                     startSignIn();
                             }
                         });

                     }
                 }

                 @Override
                 public void onCancelled(DatabaseError databaseError) {
                        //custom code to do
                 }
             });

            }
        });








        /*submit_button1.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submit1Intent = new Intent(SignupActivity.this, ProfileActivity.class);

                startActivity(submit1Intent);
            }
        })); */


/*
    }
    @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignIn() {

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "Error!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


}
*/