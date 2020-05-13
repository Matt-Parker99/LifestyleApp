package com.example.lifestyleapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainHub extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    private TextView email;
    private TextView welcomeMsg;
    private ImageView pic;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);

        // TextView handler for welcome message:
        welcomeMsg = findViewById(R.id.welcomeMsg);

        // Firebase handlers:
        mAuth = FirebaseAuth.getInstance();

        mStorageRef = FirebaseStorage.getInstance().getReference();
        // Firestore database handler:


        loadUserData();

    }

    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        loadUserData();
    }


    // shop button, starts to shop activity
    public void shop(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), ShoppingLists.class);
        startActivity(myIntent);
    }
    // message button, starts to message activity
    public void message(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(),Message.class);
        startActivity(myIntent);
    }
    // group-message button, starts to group-message activity
    public void groupMessage(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(),GroupMessage.class);
        startActivity(myIntent);
    }
    // price check button, starts to price-check activity
    public void priceCheck(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(),PriceCheck.class);
        startActivity(myIntent);
    }
    // closestShop button, starts closest-shop activity
    public void recipeTypeSelection(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), recipeTypeSection.class);
        startActivity(myIntent);
    }
    // statistics button, starts statistics activity
    public void statistics(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(),Statistics.class);
        startActivity(myIntent);
    }
    // (Bottom row - back arrow) logout button, signs out from firebase instance and returns to MainActivity
    public void logout(android.view.View view){
        FirebaseAuth.getInstance().signOut();
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);

    }
    // (Bottom row - share icon) starts share activity
    public void share(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(),Share.class);
        startActivity(myIntent);
    }

    public void editProfile(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), EditProfile.class);
        startActivity(myIntent);
    }

    void loadUserData() {
        final FirebaseUser user = mAuth.getCurrentUser();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Check that a user is logged in:
        if (user != null) {
            // Fetch the unique user ID
            String userId = user.getUid();
            // Create a reference to access the document corresponding to the userID in the users Collection:
            final DocumentReference docRef = db.collection("users").document(userId);

            try {
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // if a document exists in the database matching user's id, print their name to the welcome message
                            welcomeMsg.setText("Welcome " + document.get("name"));

                            pic = findViewById(R.id.imageView);
                            String filename = document.get("photo").toString();

                            StorageReference photoRef = mStorageRef.child("userImages/" + filename);

                            File localFile = null;
                            try {
                                localFile = File.createTempFile("image", "jpg");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            final String filePath = localFile.getPath();

                            photoRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    pic.setImageBitmap(BitmapFactory.decodeFile(filePath));
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    exception.printStackTrace();
                                }
                            });


                        } else {
                            //if no document exists for the user ID, create one with default values:
                            Map<String, Object> defaultData = new HashMap<>();
                            defaultData.put("name", "User");
                            defaultData.put("photo", "default.jpg");
                            docRef.set(defaultData);

                            // start EditProfile activity to prompt user to create a profile
                            //TODO implement a way for the user to update their name
                            Intent myIntent = new Intent(getBaseContext(), EditProfile.class);
                            startActivity(myIntent);
                        }
                    }
                });
            } catch (Exception e) {
                Log.e(e.toString(), "Error accessing user document");
                //TODO handle this error properly, (try again or log the user out?)
            }
        } else {
            //TODO Handle the situation where we get to the MainHub but user is somehow Null
        }
    }
}

