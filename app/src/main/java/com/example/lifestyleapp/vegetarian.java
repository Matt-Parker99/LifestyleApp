package com.example.lifestyleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    String mTitle[] = {"Recipe 1", "Recipe 2", "Recipe 3", "Recipe 4", "Recipe 5"};
    String mDescription[] = {"Recipe 1 Description", "Recipe 2 Description", "Recipe 3 Description", "Recipe 4 Description", "Recipe 5 Description"};
    int images[] = {R.drawable.ic_euro_symbol_black_24dp, R.drawable.ic_euro_symbol_black_24dp, R.drawable.ic_euro_symbol_black_24dp, R.drawable.ic_euro_symbol_black_24dp, R.drawable.ic_euro_symbol_black_24dp};
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
                }
                if (position ==  0) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                }
                if (position ==  0) {
                    Toast.makeText(vegetarian.this, "Recipe Description", Toast.LENGTH_SHORT).show();
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
