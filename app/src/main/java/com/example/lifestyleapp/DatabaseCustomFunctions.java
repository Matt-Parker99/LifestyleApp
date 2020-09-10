package com.example.lifestyleapp;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class DatabaseCustomFunctions {

    public final FirebaseAuth mAuth;
    public final FirebaseFirestore mStorage;
    public final StorageReference mStorageRef;
    public final FirebaseUser user;
    public final String UserId;

    // Constructor
    public DatabaseCustomFunctions(){
        this.mAuth = FirebaseAuth.getInstance();
        this.mStorage = FirebaseFirestore.getInstance();
        this.mStorageRef = FirebaseStorage.getInstance().getReference();
        this.user = this.mAuth.getCurrentUser();
        this.UserId = this.user.getUid();
    }

    public void createNewIngredient(String name, Double price){
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("name", name);
            data.put("price", price);

            mStorage.collection("products").add(data);
        } catch (Exception e) {
            Log.e("databaseCustomFunctions : CreateNewIngredients", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    public String getRecipeDescription(String recipeName) {
        return "gotRecipeDescription";
    }
}
