package com.example.lifestyleapp;

// Android Imports
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Firebase Imports

public class SignUp extends AppCompatActivity {
    private static final String TAG = "your-tag-name";
    private EditText mail;
    private EditText pass;
    private EditText cpass;

    // String variables
    private String email;
    private String password;
    private String conPassword;

    // Firebase variable
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(android.view.View view) {
        // Obtain and convert the email to a string
        mail = findViewById(R.id.email);
        email = mail.getText().toString();
        // Obtain and convert the password to a string
        pass = findViewById(R.id.password);
        password = pass.getText().toString();
        // Obtain and convert the confirmation of password to a string
        cpass = findViewById(R.id.conPassword);
        conPassword = cpass.getText().toString();


        Log.e("Email:", email);
        Log.e("Password:", password);
        Log.e("ConPassword:", conPassword);
        // If statement to check if the passwords match up
        if (password.equals(conPassword)) {
            Log.e("Pass", "1");
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.e("Pass", "2");
                    if (task.isSuccessful()) {
                        Log.e("Pass", "3");
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Log.e("Pass", "4");
                        Log.w(TAG, "createWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        } else {
            //Will need to output to screen that passwords were not the same
        }
    }

    public void back(android.view.View view) {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }

    public void updateUI(com.google.firebase.auth.FirebaseUser user) {
        if (user == null) {
            Intent myIntent = new Intent(getBaseContext(), SignUp.class);
            startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(getBaseContext(), MainHub.class);
            startActivity(myIntent);
        }
    }
}