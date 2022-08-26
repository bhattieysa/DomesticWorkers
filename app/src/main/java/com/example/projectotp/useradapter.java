package com.example.projectotp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class useradapter extends FirebaseRecyclerAdapter<Model,useradapter.myViewHolder>

{


    public useradapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder viewholder, int i, @NonNull Model model) {
        viewholder.Fullname.setText(model.getFullName());
        viewholder.Email.setText(model.getEmail());
        viewholder.Password.setText(model.getPassword());
        viewholder.CountryCode.setText(model.getCountryCode());
        viewholder.Phone.setText(model.getPhone());
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.userscard,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView Fullname,Email,Password,CountryCode,Phone;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Fullname = itemView.findViewById(R.id.nametv);
            Email = itemView.findViewById(R.id.emailtv);
            Password = itemView.findViewById(R.id.passtv);
            CountryCode = itemView.findViewById(R.id.cctv);
            Phone = itemView.findViewById(R.id.phonetv);
        }
    }
}
