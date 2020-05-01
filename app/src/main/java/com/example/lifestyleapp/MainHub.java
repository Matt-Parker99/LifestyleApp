package com.example.lifestyleapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainHub extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private StorageReference mStorageRef;
    private TextView email;
    private TextView fullName;
    private ImageView pic;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hub);

        // Firebase handlers
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        // User data handlers
        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        pic = findViewById(R.id.imageView);


        // below code currently causes program to crash

        /*if (user != null) {
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

