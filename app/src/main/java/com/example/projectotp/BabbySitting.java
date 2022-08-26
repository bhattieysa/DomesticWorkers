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
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class BabbySitting extends AppCompatActivity {
RecyclerView recyclerView;
adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_babby_sitting);
        setTitle("Babby Sitting");

        recyclerView = findViewById(R.id.babysittingrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        FirebaseRecyclerOptions<MaidRegistrationModel> options =
                new FirebaseRecyclerOptions.Builder<MaidRegistrationModel>().setQuery(FirebaseDatabase.getInstance().getReference("Maids")
                        .orderByChild("category")
                        .equalTo("BabySitting"),MaidRegistrationModel.class).build();


        adapter = new adapter(options,BabbySitting.this);
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