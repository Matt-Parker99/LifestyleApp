package com.example.lifestyleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class GazpachoSauceSpaghettiRecipe extends AppCompatActivity {

    private String userID;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String ingredients[] = {"spaghetti","cherry tomatoes","green pepper 1","garlic ½ ","red onion","sherry vinegar","Tabasco","basil"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gazpacho_sauce_spaghetti_recipe);

    }

    public void back(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), vegan.class);
        startActivity(myIntent);
    };


    public void addToShoppingList(android.view.View view){
        // Identify the user
        // Iterate through the list of ingredients
        // Perform insert statements adding the ingredients to a row of the users table which we can display in another activity
        userID = user.getUid();
        //final DocumentReference userDocRef = db.collection("users").document(userID);
        try {
            for(String ingredient : ingredients) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("users");
                myRef.child(userID).child("Shopping List").setValue(ingredient);
                // Success message
                Toast.makeText(getApplicationContext(), "Successfully Added To Shopping List!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        };
    };

    public void home(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), MainHub.class);
        startActivity(myIntent);
    };


    public void share(android.view.View view){
        // Havent developed functionality for this yet.
    };
}
