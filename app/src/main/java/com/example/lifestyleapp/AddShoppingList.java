package com.example.lifestyleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class AddShoppingList extends AppCompatActivity {

    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shopping_list);

        EditText editText = (EditText) findViewById(R.id.editText2);

        //Note id

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);

        if(noteId != -1){

            editText.setText(ShoppingLists.notes.get(noteId));
        } else {

            ShoppingLists.notes.add(""); // empty note
            noteId = ShoppingLists.notes.size() -1;
            ShoppingLists.arrayAdapter.notifyDataSetChanged();

        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                ShoppingLists.notes.set(noteId, String.valueOf(charSequence));
                //update
                ShoppingLists.arrayAdapter.notifyDataSetChanged(); // 15:12 mins

                //saving
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.lifestyleapp", Context.MODE_PRIVATE);

                HashSet<String> set = new HashSet(ShoppingLists.notes);

                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

