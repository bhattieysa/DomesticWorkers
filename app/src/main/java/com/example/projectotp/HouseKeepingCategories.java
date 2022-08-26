package com.example.projectotp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HouseKeepingCategories extends AppCompatActivity {
    CardView cardView1, cardView2, cardView3, cardView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_keeping_categories);
        setTitle("House keeping Cateories");


        cardView1 = findViewById(R.id.housecleaning);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HouseCleaning.class));
            }
        });

        cardView2 = findViewById(R.id.laundry);
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Laundry.class));
            }
        });

        cardView3 = findViewById(R.id.kitchencare);
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KitchenCare.class));
            }
        });

        cardView4 = findViewById(R.id.gardener);
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Gardener.class));
            }
        });
    }
}