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

public class SignUp extends AppCompatActivity {
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

    public void register(android.view.View view) {

        mail = findViewById(R.id.email);
        email = mail.getText().toString();
        pass = findViewById(R.id.password);
        password = pass.getText().toString();

        cpass = findViewById(R.id.conPassword);
        conPassword = cpass.getText().toString();
        System.out.println(conPassword);
        System.out.println(password);
        name = findViewById(R.id.name);
        fullname = name.getText().toString();

        // If statement to check if the passwords match up
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //This commented line was used for last project
                            //addUser(user.getUid(), fullname);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    public void back(android.view.View view) {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(myIntent);
    }

    public void addUser(String uid, String name){
        // Write message to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("TABLE NAME");
        myRef.child(uid).child("name").setValue(name);
    }

    public void updateUI(com.google.firebase.auth.FirebaseUser user) {
        if (user == null) {
            Intent myintent = new Intent(getBaseContext(), SignUp.class);
            startActivity(myintent);
        } else {
            Intent myIntent = new Intent(getBaseContext(), Home.class);
            startActivity(myIntent);
        }
    }
}