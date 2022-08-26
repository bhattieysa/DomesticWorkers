package com.example.projectotp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AllFeedbacks extends AppCompatActivity {
RecyclerView recyclerView;
feedbackadapter feedbackadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_feedbacks);
        getSupportActionBar().hide();
        recyclerView = findViewById(R.id.allmaids);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        FirebaseRecyclerOptions<feedbackModel> options =
                new FirebaseRecyclerOptions.Builder<feedbackModel>().setQuery(FirebaseDatabase.getInstance().getReference()
                        .child("FeedBacks"),feedbackModel.class).build();

        feedbackadapter = new feedbackadapter(options);
        recyclerView.setAdapter(feedbackadapter);
    }

    @Override
    protected void onStart() {
        feedbackadapter.startListening();
        super.onStart();
    }

    @Override
    protected void onStop() {
        feedbackadapter.stopListening();
        super.onStop();
    }
}