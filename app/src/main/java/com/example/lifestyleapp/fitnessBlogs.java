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

public class fitnessBlogs extends AppCompatActivity {


    ListView listView;
    String mTitle[] = {"Nerd Fitness","Breaking Muscle","Pumps & Iron","Robertson Training Systems"};
    String mDescription[] = {"Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!","Click to find out more!",};
    int images[] = {R.drawable.nerd_fitness_400x400,R.drawable.breaking_muscle_400x400,R.drawable.pumps_and_iron_400x400,R.drawable.robertson_training_systems_400x400,};
    String mUrls[] = {"https://www.nerdfitness.com/blog/","https://breakingmuscle.com/","https://pumpsandiron.com/","https://robertsontrainingsystems.com/"};
    // so our images and other things are set in array

    // now paste some images in drawable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_blogs);

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
                    Toast.makeText(fitnessBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[0]));
                    startActivity(myIntent);
                }
                if (position ==  1) {
                    Toast.makeText(fitnessBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[1]));
                    startActivity(myIntent);
                }
                if (position ==  2) {
                    Toast.makeText(fitnessBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[2]));
                    startActivity(myIntent);
                }
                if (position ==  3) {
                    Toast.makeText(fitnessBlogs.this, "Re-directing to blog webpage", Toast.LENGTH_SHORT).show();
                    // We will use an intent which will re-direct to a web page
                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrls[3]));
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



