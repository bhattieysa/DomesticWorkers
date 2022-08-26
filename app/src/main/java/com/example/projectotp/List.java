package com.example.projectotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;

public class List extends AppCompatActivity {
RecyclerView recyclerView;
    adapter adapter;
    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        constraintLayout = findViewById(R.id.constraintLayout);
        setTitle("All Maids");




        recyclerView = findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i=0; i < 2; i++)
        {
            Toast.makeText(this, "Swipe Left To Delete Any Maid", Toast.LENGTH_LONG).show();
        }








        FirebaseRecyclerOptions<MaidRegistrationModel> options =
              new FirebaseRecyclerOptions.Builder<MaidRegistrationModel>().setQuery(FirebaseDatabase.getInstance().getReference()
                .child("Maids"),MaidRegistrationModel.class).build();
        adapter = new adapter(options,List.this);
       recyclerView.setAdapter(adapter);


        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }
    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Snackbar snackbar = Snackbar.make(constraintLayout,"Item Deleted",Snackbar.LENGTH_LONG);
            snackbar.show();
            adapter.deleteItem(viewHolder.getAdapterPosition());


        }
    };

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
        searchView.setQueryHint("Search Maids By Name");
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Maids").orderByChild("fullName").startAt(query).endAt(query+"\uf8ff"),MaidRegistrationModel.class)
                        .build();


        adapter = new adapter(options,this);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}