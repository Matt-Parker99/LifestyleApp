package com.example.lifestyleapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class mainHub extends AppCompatActivity {

    // Firebase variable
    private FirebaseAuth mAuth;
    //Buttons
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);
        mAuth = FirebaseAuth.getInstance();
    }
    public void logout (android.view.View view) {

        try {
            // sign out
            mAuth.signOut();
            // retutn to login
            Intent myIntent = new Intent(getBaseContext(), Login.class);
            startActivity(myIntent);
            finish();

        } catch (Exception e) {
            // an error
            //TODO handle this error
        }


    }
}
