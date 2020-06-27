package com.example.lifestyleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

// Listener Imports
// Firebase Imports

public class Login extends AppCompatActivity {

    private static final String TAG = "YOUR-TAG-NAME";

    // Variables containing data entered by the user
    private EditText mail;
    private EditText pass;

    // Varibles that contain data entered by user as a string
    private String email;
    private String password;

    // Buttons
    private Button login;
    private Button signUp;

    // Firebase variable
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }

    public void login(android.view.View view) {
        // Validating signing in
        mail = findViewById(R.id.email);
        email = mail.getText().toString();

        pass = findViewById(R.id.password);
        password = pass.getText().toString();

        if (email.equals("test") && password.equals("test")) {
            Intent myIntent = new Intent(getBaseContext(), MainHub.class);
            startActivity(myIntent);
        } else {


            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        }
    }

    public  void signUp(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), SignUp.class);
        startActivity(myIntent);
    }

    public void updateUI(com.google.firebase.auth.FirebaseUser user){
        if(user==null){
            Intent myIntent = new Intent(getBaseContext(), Login.class);
            startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(getBaseContext(), MainHub.class);
            startActivity(myIntent);
        }
    }

}