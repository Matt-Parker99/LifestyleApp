package com.example.lifestyleapp;

// Android Imports
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.widget.ProgressBar;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

// Firebase Imports

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashmap;

public class SignUp extends AppCompatActivity {
    private static final String TAG = "your-tag-name";
    private EditText mail;
    private EditText pass;
    private EditText cpass;
    private EditText name;

    // String variables
    private String email;
    private String password;
    private String conPassword;
    private String fullname;

    // Firebase variable

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
    }

    public void register(android.view.View view) {

        mail = findViewById(R.id.email);
        email = mail.getText().toString();
        pass = findViewById(R.id.password);
        pasword = pass.getText().toString();

        cpass = findViewById(R.id.conPassword);
        conPassword = cpass.getText().toString();
        System.out.println(conPassword);
        System.out.println(password);
        name = findViewById(R.id.fullname);
        fullname = name.getText().toString();

        // If statement to check if the passwords match up

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            addUser(user.getUid(), fullname);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });

    }


    public void goToLogin(android.view.View view) {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }

    public void AddUser(String uid, String name){
        // Write message to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TABLE NAME");
        //Code that was in previous app
        //myRef.setValue("Hello, World!");
        //String id = myRef.push().getKey();
        myRef.child(uid).child("name").setValue(name);

    }

    public void updateUI(com.google.firebase.auth.FirebaseUser user){
        if(user==null){
            Intent myintent = new Intent(getBaseContext(), homeActivity.class);
            startActivity(myintent);
        } else {
            Intent myIntent = new Intent(getBaseContext(), "Next page Maybe a more details page");
            startActivity(myIntent);
        }
    }

    public void limiter(View view){

    }

    public void getInfo(View view){

    }

}










