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
import com.google.firebase.storage.StorageReference;

public class spicyLentilBurgers extends AppCompatActivity {

    private String userID;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String ingredients[] = { "vegetable oil 2 tsp, plus a little extra for frying","red onions 1½, finely chopped, ½, thinly sliced","garlic paste 2 tsp","ginger paste 2 tsp","hot curry powder 2 tbsp","brown lentils 2 x 400g tins, drained, rinsed, then drained again","plain flour 4 tbsp","white wine vinegar 1 tbsp","seeded burger buns 4, halved and toasted","mango chutney 4 tsp","lettuce leaves 4","tomato slices 4","coriander leaves (optional)"
    };
    // quantity units to bo added in the ingredient object file on DB
    Double quantaties[] = {2.0,0.5,0.5,2.0,1.0,2.0,.800,4.0,1.0,4.0,4.0,4.0,1.0};
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
            int i = 0;
            for(String ingredient : ingredients) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference newIngedientRef = database.getReference("users/" + userID + "/list").push();
                newIngedientRef.setValue("id", ingredient);
                newIngedientRef.setValue("quantity", quantaties[i]);
                i += 1;
            }
            // Success message
            Toast.makeText(getApplicationContext(), "Successfully Added To Shopping List!", Toast.LENGTH_SHORT).show();

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


