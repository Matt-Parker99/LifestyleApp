package com.example.lifestyleapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.widget.ProgressBar;
import android.util.Log;
import android.widget.Toast;

// Listener Imports
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

// Firebase Imports
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static final String TAG ="YOUR-TAG-NAME";

    // Variables containing data entered by the user
    private EditText mail;
    private EditText pass;

    // Varibles that contain data entered by user as a string
    private String email;
    private String password;

    // Buttons
    private Button login;
    private Button signUp;

    // Firebase variable
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = Firebase.getInstance();
    }

    public void Login(android.view.View view) {
        // Validating signing in
        mail = findViewById(R.id.email);
        email = mail.getText().toString();

        pass = findViewById(R.id.password);
        password = pass.getText().toString();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public  void signUp(android.view.View view){
        Intent myIntent = new Intent(getBaseContext(), SignUp.class);
        startActivity(myIntent);
    }

    public void updateUI(com.google.firebase.auth.FirebaseUser user){
        if(user==null){
            Intent myIntent = new Intent(getaseContext(), SignUp.class);
            startActivity(myIntent);
        } else {
            Intent myIntent = new Intent(getBaseContext(), homeActivity.class);
            startActivity(myIntent);
        }
    }

    // **CODE FROM PREVIOUS APPLICATION WHICH COULD BE USED***
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //editText = (EditText) findViewById(R.id.editText);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);
    } */

    //@Override
    //public void onOpen(Room room) {
    //System.out.println("Connected to room");
    //}

    //@Override
    //public void onOpenFailure(Room room, Exception ex) {
    //System.err.println(ex);
    //}

    //@Override
    //public void onMessage(Room room, com.scaledrone.lib.Message receivedMessage) {
    //TODO
    //}

}



    }
}
