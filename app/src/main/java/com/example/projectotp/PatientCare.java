package com.example.projectotp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PatientCare extends AppCompatActivity {
RecyclerView recyclerView;
adapter2 adapter2;
ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_care);
        setTitle("Patient Care");


        recyclerView = findViewById(R.id.patientrecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        FirebaseRecyclerOptions<MaidRegistrationModel> options =
//                new FirebaseRecyclerOptions.Builder<MaidRegistrationModel>().setQuery(FirebaseDatabase.getInstance().getReference("Maids")
//                        .orderByChild("category")
//                        .equalTo("PatientCare"),MaidRegistrationModel.class).build();
//
//
//        adapter2 = new adapter2(options,PatientCare.this);
//        recyclerView.setAdapter(adapter2);


        FirebaseRecyclerOptions<MaidRegistrationModel> options =
                new FirebaseRecyclerOptions.Builder<MaidRegistrationModel>().setQuery(FirebaseDatabase.getInstance().getReference("Maids")
                .orderByChild("category").equalTo("PatientCare"),MaidRegistrationModel.class).build();

        adapter2 = new adapter2(options,PatientCare.this);
        recyclerView.setAdapter(adapter2);


    }

    @Override
    protected void onStart() {
        adapter2.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        adapter2.stopListening();
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


        adapter2 = new adapter2(options,this);
        adapter2.startListening();
        recyclerView.setAdapter(adapter2);
    }
}