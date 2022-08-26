package com.example.projectotp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class UsersList extends AppCompatActivity {
    RecyclerView recyclerView;
    useradapter useradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().hide();


        recyclerView = findViewById(R.id.userrecview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>().setQuery(FirebaseDatabase.getInstance().getReference()
                        .child("Users"),Model.class).build();


        useradapter = new useradapter(options);
        recyclerView.setAdapter(useradapter);




    }

    @Override
    protected void onStart() {
        useradapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        useradapter.stopListening();
        super.onStop();
    }
}

