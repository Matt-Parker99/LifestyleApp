package com.example.lifestyleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.annotations.Nullable;

public class vegetarian extends AppCompatActivity {

    ListView listView;
    String mTitle[] = {"Super-seedy Salad With Tahini Dressing", "Pasta salad with bocconcini, capers and tomatoes", "Courgetti with pesto and balsamic tomatoes", "Rigatoni with broccoli pesto", "Yaki udon noodles"};
    String mDescription[] = {"549", "395", "459", "534", "271"};
    int images[] = {R.drawable.superseedysalad, R.drawable.pastasalad, R.drawable.courgette, R.drawable.rigatonni, R.drawable.yakinoodles};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegetarian);

        listView = findViewById(R.id.listView);
        // now create an adapter class

        vegetarian.MyAdapter adapter = new vegetarian.MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            // We will just add the recipes to this further down the line
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myintent = new Intent(getBaseContext(), vegetarian.class); // Class needs to be created and added
                    startActivity(myintent);
                }
                if (position ==  1) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myintent = new Intent(getBaseContext(), vegetarian.class); // Class needs to be created and added
                    startActivity(myintent);
                }
                if (position ==  2) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myintent = new Intent(getBaseContext(), vegetarian.class); // Class needs to be created and added
                    startActivity(myintent);
                }
                if (position ==  3) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myintent = new Intent(getBaseContext(), vegetarian.class); // Class needs to be created and added
                    startActivity(myintent);
                }
                if (position ==  4) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myintent = new Intent(getBaseContext(), vegetarian.class); // Class needs to be created and added
                    startActivity(myintent);
                }
            }
        });
        // so item click is done now check list view
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String rTitle[];
        String rDescription[];
        int rImgs[];

        MyAdapter (Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);




            return row;
        }
    }
}
