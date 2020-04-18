package com.example.lifestyleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;

import android.os.Bundle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(authStateListener);
    }

    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth mAuth) {
            FirebaseUser mUser = mAuth.getCurrentUser();

            if (mUser == null) {
                Intent myIntent = new Intent(MainActivity.this, Login.class);
                startActivity(myIntent);
                finish();
            }
            if (mUser != null) {
                Intent myIntent = new Intent(MainActivity.this, Home.class);
                startActivity(myIntent);
                finish();
            }
        }
    };

    public void login(android.view.View view) {
        Intent myIntent = new Intent(getBaseContext(), Login.class);
        startActivity(myIntent);
    }

    public void signUp(android.view.View view) {
        Intent myIntent = new Intent(getBaseContext(), SignUp.class);
        startActivity(myIntent);
    }
}
