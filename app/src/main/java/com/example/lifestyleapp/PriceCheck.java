package com.example.lifestyleapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class PriceCheck extends AppCompatActivity {

    public DatabaseCustomFunctions dbRef;


    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_check);

        listView = findViewById(R.id.productsView);

        final Context myContext = this;

        // The following code adapts
        dbRef.mStorage.collection("products")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            int listSize = task.getResult().size();
                            String title[] = new String[listSize];
                            String price[] = new String[listSize];

                            int i = 0;

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                title[i] = document.get("name").toString();
                                price[i] = document.get("price").toString();
                                i += 1;
                            }
                            MyAdapter adapter = new MyAdapter(myContext, title, price);
                            listView.setAdapter(adapter);
                        } else {
                            Log.e("PriceCheck", "Error getting documents: ", task.getException());
                        }
                    }
                });


        // now set item click on list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        // so item click is done now check list view
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String title[];
        String price[];


        MyAdapter (Context c, String title[], String price[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.title = title;
            this.price = price;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            myTitle.setText(title[position]);
            myDescription.setText(price[position]);




            return row;
        }
    }
}
