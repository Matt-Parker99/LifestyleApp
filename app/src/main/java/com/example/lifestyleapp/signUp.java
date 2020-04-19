package com.example.lifestyleapp;

// Android Imports
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

// Firebase Imports
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signUp extends AppCompatActivity {
    private static final String TAG = "your-tag-name";
    private EditText mail;
    private EditText pass;
    private EditText cpass;
    private EditText name;

    // String variables
    private String email;
    private String password;
    private String conPassword;
    private String fullname;

    // Firebase variable
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(android.view.View view) {

        mail = findViewById(R.id.email);
        email = mail.getText().toString();

        pass = findViewById(R.id.password);
        password = pass.getText().toString();

        cpass = findViewById(R.id.conPassword);
        conPassword = cpass.getText().toString();

        name = findViewById(R.id.name);
        fullname = name.getText().toString();

        Log.e("Name:", fullname);
        Log.e("Email:", email);
        Log.e("Password:", password);
        Log.e("ConPassword:", conPassword);
        // If statement to check if the passwords match up
        if (password.equals(conPassword)) {
            Log.e("Pass", "1");
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Log.e("Pass", "2");
                    if (task.isSuccessful()) {
                        Log.e("Pass", "3");
                        Log.d(TAG, "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        Log.e("Pass", "4");
                        Log.w(TAG, "createWithEmail:failure", task.getException());
                        Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                }
            });
        } else {
            //Will need to output to screen that passwords were not the same
        }
    }

    public void back(android.view.View view) {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }

    //Will prob be used to store usernames etc.
    public void addUser(String uid, String name){
        // Write message to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TABLE NAME");
        myRef.child(uid).child("name").setValue(name);
    }

    public void updateUI(com.google.firebase.auth.FirebaseUser user) {
        if (user == null) {
            Intent myIntent = new Intent(getBaseContext(), signUp.class);
            startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(getBaseContext(), Home.class);
            startActivity(myIntent);
        }
    }
}