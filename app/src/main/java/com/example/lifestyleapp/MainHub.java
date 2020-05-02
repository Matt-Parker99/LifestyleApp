package com.example.lifestyleapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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
        final FirebaseUser user = mAuth.getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        // Firestore database handler:
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
                        if(document.exists()){
                            welcomeMsg.setText("Welcome "+ document.get("name"));
                        }else{
                            //if no document exists for the user ID, create one with default values:
                            Map<String, Object> defaultData = new HashMap<>();
                            defaultData.put("name", "User");
                            defaultData.put("profileImg","default.jpg");
                            //TODO create a default profile image
                            docRef.set(defaultData);
                        }
                    }
                });
            }
            catch(Exception e){
                Log.e(e.toString(), "Error accessing user document");
                //TODO handle this error properly, (try again or log the user out?)
            }
        }else{
            //TODO Handle the situation where we get to the MainHub but user is somehow Null
        }


        // below block of code currently causes program to crash
        /*

        email = findViewById(R.id.email);
        pic = findViewById(R.id.imageView);

        if (user != null) {
            String email1 = user.getEmail();
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("users/" + user.getUid());



            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    DataSnapshot snapshot = dataSnapshot.child("Name");
                    String name = snapshot.getValue().toString();
                    fullName.setText(name);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
            email.setText(email1);
            try {
                url = user.getUid();
                final File localFile = File.createTempFile(url, "jpg");
                StorageReference Ref = mStorageRef.child(url + ".jpg");
                Ref.getFile(localFile)
                        .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                // Successfully downloaded data to local file
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                pic.setImageBitmap(bitmap);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle failed download
                        // ...
                    }
                });
            } catch (Exception e) {
            }
        }*/
    }

    // Button functions

    // shop button, starts to shop activity
    public void shop(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(),Shop.class);
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
    public void closestShop(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(),ClosestShop.class);
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
}

