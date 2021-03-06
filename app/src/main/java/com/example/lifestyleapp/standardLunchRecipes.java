package com.example.lifestyleapp;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.annotations.Nullable;

public class standardLunchRecipes extends AppCompatActivity {


    ListView listView;
    String mTitle[] = {"boxing-day-banh-mi"," bean-tomato-watercress-salad"," vegan-chickpea-curry-jacket-potato"," beetroot-lentil-tabbouleh"," spring-tabbouleh",  "sweet-potato-peanut-butter-chilli-quesadillas","veggie-fajitas","sweet-potato-hash-eggs-smashed-avo","green-pesto-minestrone","veggie-loaded-flatbread","beetroot-humous-toasts-olives-mint"};
    String mDescription[] = {"Click here!","Click here!","Click here!","Click here!","Click here!","Click here!","Click here!","Click here!","Click here!","Click here!","Click here!"};
    int images[] = {R.drawable.veganlunch1,R.drawable.veganlunch2,R.drawable.veganlunch3,R.drawable.veganlunch4,R.drawable.veganlunch5,R.drawable.veganquesadillas,R.drawable.veggiefajitas,R.drawable.sweetpotatoharissacakeswithpoachedeggs,R.drawable.pestominestrone,R.drawable.veggieloadedflatbread,R.drawable.beetroottoast};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_lunch_recipes);

        listView = findViewById(R.id.listView);
        // now create an adapter class

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);
        // there is my mistake...
        // now again check this..

        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    // Once User Clicks the item they are displayed a recipe description prompt
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    // An Intent is created for the recipe class to be loaded
                    Intent myIntent = new Intent(getBaseContext(), veganLunch1.class);
                    // The intent is then activated by the startActivity Method and the recipe is displayed
                    startActivity(myIntent);
                }
                if (position ==  1) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), veganLunch2.class);
                    startActivity(myIntent);
                }
                if (position ==  2) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), veganLunch3.class);
                    startActivity(myIntent);
                }
                if (position ==  3) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), veganLunch4.class);
                    startActivity(myIntent);
                }
                if (position ==  4) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), veganLunch5.class);
                    startActivity(myIntent);
                }
                if (position ==  5) {
                    // Once User Clicks the item they are displayed a recipe description prompt
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    // An Intent is created for the recipe class to be loaded
                    Intent myIntent = new Intent(getBaseContext(), vegLunch1.class);
                    // The intent is then activated by the startActivity Method and the recipe is displayed
                    startActivity(myIntent);
                }
                if (position ==  6) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), vegLunch2.class);
                    startActivity(myIntent);
                }
                if (position ==  7) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), vegLunch3.class);
                    startActivity(myIntent);
                }
                if (position ==  8) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), vegLunch4.class);
                    startActivity(myIntent);
                }
                if (position ==  9) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), vegLunch5.class);
                    startActivity(myIntent);
                }
                if (position ==  10) {
                    Toast.makeText(standardLunchRecipes.this, "Accessing Recipe", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), vegLunch6.class);
                    startActivity(myIntent);
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


