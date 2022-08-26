package com.example.projectotp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Laundry extends AppCompatActivity {
    RecyclerView recyclerView;
    adapter adapter;
    Query query;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);
        setTitle("Laundry");




        recyclerView = findViewById(R.id.laundryrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference("Maids");




        FirebaseRecyclerOptions<MaidRegistrationModel> options =
                new FirebaseRecyclerOptions.Builder<MaidRegistrationModel>().setQuery(FirebaseDatabase.getInstance().getReference("Maids")
                        .orderByChild("category")
                        .equalTo("Laundry"),MaidRegistrationModel.class).build();


        adapter = new adapter(options,Laundry.this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        adapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        adapter.stopListening();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.searchview);

        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Maids By City");
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                processsearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {

                processsearch(query);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String query) {


        FirebaseRecyclerOptions<MaidRegistrationModel> options =
                new FirebaseRecyclerOptions.Builder<MaidRegistrationModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Maids").orderByChild("city").startAt(query).endAt(query+"\uf8ff"),MaidRegistrationModel.class)
                        .build();


        adapter = new adapter(options,this);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
    }
