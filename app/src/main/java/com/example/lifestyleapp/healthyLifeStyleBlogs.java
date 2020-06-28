package com.example.lifestyleapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class healthyLifeStyleBlogs extends AppCompatActivity {


    ListView listView;
    String mTitle[] = {"Delish Knowledge","The Real Food Dietitians","Fit Foodie Finds","Toby Amidor Nutrition","Peanut Butter Fingers","The Healthy Maven","Fitful Focus","Nutrition Twins","The Art of Healthy Living"};
    String mDescription[] = {"Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!",};
    int images[] = {R.drawable.delish_knowledge_400x400,R.drawable.the_real_food_dietitians_400x400,R.drawable.fit_foodie_finds_400x400,R.drawable.tony_amidor_400x400,R.drawable.pb_fingers_400x400,R.drawable.healthy_maven_400x400,R.drawable.fitful_focus_400x400,R.drawable.nutrition_twins,R.drawable.art_of_healthy_living_400x400};
    String mUrls[] = {"https://www.healthline.com/nutrition","https://therealfoodrds.com/","https://fitfoodiefinds.com/","https://tobyamidornutrition.com/my-blog/","https://www.pbfingers.com/","https://www.thehealthymaven.com/","https://fitfulfocus.com/","https://nutritiontwins.com/blog/","https://artofhealthyliving.com/about/"};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthy_life_style_blogs);

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
                    // Display to the user that the webpage is loading
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    // The URL is found through the use of indexing of the list of urls
                    // The urls parsed using the .parse function to check if it's valid
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[0]));
                    // The intent to the page is activated using the startActivity method and is loaded
                    startActivity(myIntent);
                }
                if (position ==  1) {
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[1]));
                    startActivity(myIntent);
                }
                if (position ==  2) {
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[2]));
                    startActivity(myIntent);
                }
                if (position ==  3) {
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[3]));
                    startActivity(myIntent);
                }
                if (position ==  4) {
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[4]));
                    startActivity(myIntent);
                }

                if (position ==  5) {
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[5]));
                    startActivity(myIntent);
                }

                if (position ==  6) {
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[6]));
                    startActivity(myIntent);
                }

                if (position ==  7) {
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[7]));
                    startActivity(myIntent);
                }

                if (position ==  8) {
                    Toast.makeText(healthyLifeStyleBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[8]));
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

    public void home(android.view.View view) {
        Intent myIntent = new Intent(getBaseContext(), MainHub.class);
        startActivity(myIntent);
    }
}


