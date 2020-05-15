package com.example.lifestyleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class avocadoOnToast extends AppCompatActivity {

    String ingredients[] = {"",""," "};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avocado_on_toast);
    }

    public void back(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), vegan.class);
        startActivity(myIntent);
    }


    public void addToShoppingList(android.view.View view){
        // Will research on how we can add the ingredients to a row of a table for a specific user
        Intent myIntent = new Intent(getBaseContext(), avocadoOnToast.class);
        // Database/Firebase stuff so we could get the instance or whatever
        // Iterate through the ingredients list and insert into table one by one?
    }

    public void home(android.view.View view){
        // User is sent back to the mainhub
        Intent myintent = new Intent(getBaseContext(), MainHub.class);
        startActivity(myintent);
    }

    public void share(android.view.View view){
        // Can be added later , user can share a recipe or something can be removed if too taxing to do
        // Intent myintent = new Intent (getBaseContext(), share.class);
        // startActivity(myintent);
    }
}
