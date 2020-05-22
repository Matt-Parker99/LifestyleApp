package com.example.lifestyleapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class databaseCustomFunctions {

    private final FirebaseAuth mAuth;
    private final StorageReference mStorageRef;
    private final FirebaseFirestore db;
    private final FirebaseUser user;
    private final String UserId;

    // Constructor
    public databaseCustomFunctions(FirebaseAuth mAuth, StorageReference mStorageRef, FirebaseFirestore db){

        this.mAuth = mAuth;
        this.mStorageRef = mStorageRef;
        this.db = db;
        this.user = this.mAuth.getCurrentUser();
        this.UserId = this.user.getUid();
    }

    public void createNewIngredient(String name, Double price){
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("price", price);

            db.collection("products").add(data);
        } catch (Exception e) {
            Log.e("databaseCustomFunctions : CreateNewIngredients", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
