package com.example.lifestyleapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class recipeTypeSection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_type_section);

        //TODO Implement Closest-Shop
    }


    public void vegan(android.view.View view) {
        Intent intent = new Intent(getBaseContext(), vegan.class);
        startActivity(intent);
    }


    public void vegetarian(android.view.View view) {
        Intent intent = new Intent(getBaseContext(), vegetarian.class);
        startActivity(intent);
    }

    public void standard(android.view.View view) {
        Intent intent = new Intent(getBaseContext(), standardRecipes.class);
        startActivity(intent);
    }

    // (Bottom row - share icon) starts share activity
    public void share(android.view.View view) {
        Intent myIntent = new Intent(getBaseContext(), Share.class);
        startActivity(myIntent);
    }

    public void back(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), MainHub.class);
        startActivity(myIntent);
    }

    public void home(android.view.View view) {
        Intent myIntent = new Intent(getBaseContext(), MainHub.class);
        startActivity(myIntent);
    }



}
