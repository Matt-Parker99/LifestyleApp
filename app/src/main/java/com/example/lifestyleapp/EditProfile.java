package com.example.lifestyleapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EditProfile extends AppCompatActivity {
    ImageView img;
    EditText nameText;
    private StorageReference mStorageRef;
    private FirebaseAuth mAuth;
    public Uri imguri;
    private String userID;
    private FirebaseUser user;

    FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        img = (ImageView) findViewById(R.id.profile);
        nameText = (EditText) findViewById(R.id.editText);

        final DocumentReference docRef = db.collection("users").document(user.getUid());

        try {
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        nameText.setText(document.get("name").toString());
                    }
                }
            });
        }catch (Exception e) {
            Log.e(e.toString(), "Error accessing user document");
        }
    }

    // Starts an external activity to choose a photo from the phone's storage
    public void chooseFile(android.view.View view) {
        Intent photoPickIntent = new Intent();
        photoPickIntent.setType("image/*");
        photoPickIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(photoPickIntent, 1);
    }
    // Upon selecting a photo, checks the result and sets the data to the imageView
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imguri = data.getData();
            img.setImageURI(imguri);
        }
    }

    public void uploadFile(android.view.View view) {
        // Retrieve user's unique ID
        userID = user.getUid();
        final DocumentReference userDocRef = db.collection("users").document(userID);
        if (!(imguri == null)) {
            try {
                String fileName = userID + "." + getExtension(imguri);
                // Create a reference in storage to a new file "(userID).(file extension)
                StorageReference ref = mStorageRef.child("userImages/" + fileName);
                ref.putFile(imguri);
                // Update file reference in user's database entry:
                userDocRef.update("photo", fileName);
                Toast.makeText(getApplicationContext(), "Upload Successful!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("EditProfile", "Error uploading photo");
                Toast.makeText(getApplicationContext(), "Error uploading photo", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "No new Image selected", Toast.LENGTH_SHORT).show();
        }
        // Update the user's name:
        try {
            userDocRef.update("name", nameText.getText().toString());
        }catch(Exception e){
            Log.e("EditProfile", "Name not updated");
        }
    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }

    public void skip(android.view.View view){
        finish();

    }

}

