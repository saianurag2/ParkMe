package com.example.toshiba.parkme;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener1;

    private EditText mEmailField;
    private EditText mPasswordField;
    private Button submit_button21;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = (EditText) findViewById(R.id.name_field);
        mPasswordField = (EditText) findViewById(R.id.password_field);
        submit_button21 = (Button) findViewById(R.id.submit112);

        mAuth = FirebaseAuth.getInstance();
     /*   mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null) {
                    //Intent User Account
                    startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                    finish(); //back button will close the app
                }

            }
        };
*/
        submit_button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startSignIn();
                String email = mEmailField.getText().toString();
                final String password = mPasswordField.getText().toString();
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Fields Are Empty", Toast.LENGTH_LONG).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Email/password incorrect", Toast.LENGTH_LONG).show();
                            }
                            else {
                                startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                                finish(); //back button will close the app
                            }
                        }
                    });
                }
            }
        });


    }

  /*  @Override
    protected void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    */

 /*   private void startSignIn() {
        String email = mEmailField.getText().toString();
        final String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "Fields Are Empty", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "Email/password incorrect", Toast.LENGTH_LONG).show();
                    }
                    else {
                        startActivity(new Intent(LoginActivity.this,ProfileActivity.class));
                        finish(); //back button will close the app
                    }
                }
            });
        }
    }
*/





 /*       Button submit_button2 = (Button) findViewById(R.id.submit2);

        submit_button2.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submit2Intent = new Intent(LoginActivity.this, ProfileActivity.class);

                startActivity(submit2Intent);
            }
        })); */


}
