package com.example.lifestyleapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class selectBlogType extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_blog_type);

        //TODO Implement Closest-Shop
    }


    public void fitness(android.view.View view) {
        Intent intent = new Intent(getBaseContext(), fitnessBlogs.class);
        startActivity(intent);
    }


    public void lifestyle(android.view.View view) {
        Intent intent = new Intent(getBaseContext(), healthyLifeStyleBlogs.class);
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
