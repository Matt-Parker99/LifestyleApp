package com.example.lifestyleapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

public class vegetarianSmoothie4 extends AppCompatActivity {

    private String userID;
    private FirebaseUser user;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String ingredients[] = {"10 strawberries",", hulled (approx 175g)","1 small bananna","100ml orange juice"};
    // quantity units to bo added in the ingredient object file on DB
    Double quantaties[] = {10.0,0.175,1.0,0.100};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetarian_smoothie4);

    }

//    public void back(android.view.View view){
//        Intent myIntent = new Intent(getBaseContext(), vegetarianSmoothies.class);
//        startActivity(myIntent);
//    };

    public void viewRecipeOnline(android.view.View view){
        Toast.makeText(vegetarianSmoothie4.this, "Accessing Website Of Recipe", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bbcgoodfood.com/recipes/strawberry-smoothie"));
        startActivity(myIntent);
    }


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
