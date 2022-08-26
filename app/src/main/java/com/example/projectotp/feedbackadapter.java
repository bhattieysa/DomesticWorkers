package com.example.projectotp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class feedbackadapter extends FirebaseRecyclerAdapter<feedbackModel,feedbackadapter.myViewHolder> {

    public feedbackadapter(@NonNull FirebaseRecyclerOptions<feedbackModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i, @NonNull feedbackModel feedbackModel) {
        myViewHolder.Feedback.setText(feedbackModel.getFeedback());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedbackcard,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView Feedback;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Feedback = itemView.findViewById(R.id.fbtv);
        }
    }
}
