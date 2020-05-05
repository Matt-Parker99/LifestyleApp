package com.example.lifestyleapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;

public class shoppingLists extends AppCompatActivity {

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater menuInflater = getMenuInflater(); // 17:38
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.add_note) {

            Intent intent = new Intent(getApplicationContext(), addShoppingList.class);
            startActivity(intent);

            return true;
        }

        return false;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_lists);

        ListView listView = (ListView) findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.lifestyleapp", Context.MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);

        if (set == null) {
            notes.add("Example note");
        } else {
            notes = new ArrayList(set);
        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), addShoppingList.class);
                intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

        // 23:15mins
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                new AlertDialog.Builder(shoppingLists.this)
//                        .setIcon(android.R.drawable.ic_dialog_alert)
//                        .setTitle("Are you sure?")
//                        .setMessage("Do you want to delete this list")
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public Object setNegativeButton(String no, Object o) {
//                            }
//
//                            @Override
//                            public void onClick(DialogInterface dialog, int i) {
//
//                                notes.remove(i);
//                                //update
//                                arrayAdapter.notifyDataSetChanged();
//                            }
//                        }
//
//            .setNegativeButton("No",null)
//            .show
//
//
//            return true;
    }
}



