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

public class vegan extends AppCompatActivity {


    ListView listView;
    String mTitle[] = {"Avocado On Toast", "Gazpacho Sauce Spaghetti", "Spicy Lentil Burgers", "Green Thai Tofu Curry", "Vegan Mixed Berry Pancakes"};
    String mDescription[] = {"456 Calories", "361 Calories", "496 Calories", "483 Calories", "464 Calories"};
    int images[] = {R.drawable.avocadotoast, R.drawable.spaghetti, R.drawable.burger, R.drawable.curry, R.drawable.pancakes};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vegan);

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
                    Toast.makeText(vegan.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    // An Intent is created for the recipe class to be loaded
                    Intent myIntent = new Intent(getBaseContext(), avocadoOnToast.class);
                    // The intent is then activated by the startActivity Method and the recipe is diaplyed
                    startActivity(myIntent);
                }
                if (position ==  1) {
                    Toast.makeText(vegan.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), GazpachoSauceSpaghettiRecipe.class);
                    startActivity(myIntent);
                }
                if (position ==  2) {
                    Toast.makeText(vegan.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), spicyLentilBurgers.class);
                    startActivity(myIntent);
                }
                if (position ==  3) {
                    Toast.makeText(vegan.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), tofuCurry.class);
                    startActivity(myIntent);
                }
                if (position ==  4) {
                    Toast.makeText(vegan.this, "Recipe Description", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(getBaseContext(), veganMixedBerryPancakes.class);
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

