package com.example.lifestyleapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class avocadoOnToast extends AppCompatActivity {

    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    //public Uri imguri;
    private String userID;
    private FirebaseUser user;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String ingredients[] = {"chick peas","white meso paste","seasame oil","lemon","avocado","rye bread","seasame seeds","spring onion"};
    // Quantity list
    Double quantities[] = {.400,1.0,1.0,0.5,0.5,1.0,4.0,1.0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avocado_on_toast);
//        mAuth = FirebaseAuth.getInstance();
//        user = mAuth.getCurrentUser();
//        mStorageRef = FirebaseStorage.getInstance().getReference();


    }

    public void back(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), vegan.class);
        startActivity(myIntent);
    }


    public void addToShoppingList(android.view.View view){
        userID = user.getUid();

     try {

         int i = 0;
         for(String ingredient: ingredients){
             FirebaseDatabase database = FirebaseDatabase.getInstance();
             DatabaseReference newIngredientRef = database.getReference("users/" + userID + "/list").push();
             newIngredientRef.setValue("id", ingredient);
             newIngredientRef.setValue("quantity", quantities[i]);
             i = i+1;
         }
         // Success message
         Toast.makeText(getApplicationContext(),"Successfully Added To Shopping List!",Toast.LENGTH_SHORT).show();

     } catch (Exception e) {
         e.printStackTrace();
     };

    };


    

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
