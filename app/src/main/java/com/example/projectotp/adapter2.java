package com.example.projectotp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class adapter2 extends FirebaseRecyclerAdapter<MaidRegistrationModel,adapter2.viewHolder>{
    FirebaseRecyclerOptions<MaidRegistrationModel> options;
    Context ctx;
    public adapter2(@NonNull FirebaseRecyclerOptions<MaidRegistrationModel> options, Context ctx) {
        super(options);
        this.options = options;
        this.ctx = ctx;
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder viewHolder, int i, @NonNull MaidRegistrationModel maidRegistrationModel) {
        viewHolder.FulName.setText(maidRegistrationModel.getFullName());
        viewHolder.Mobile.setText(maidRegistrationModel.getNumber());
        viewHolder.CAtegory.setText(maidRegistrationModel.getCategory());
        viewHolder.Gender.setText(maidRegistrationModel.getGender());
        viewHolder.Age.setText(maidRegistrationModel.getAge());
        viewHolder.City.setText(maidRegistrationModel.getCity());
        viewHolder.Education.setText(maidRegistrationModel.getEducation());
        viewHolder.Experience.setText(maidRegistrationModel.getExperience());
        viewHolder.Religion.setText(maidRegistrationModel.getReligion());
        viewHolder.Cnic.setText(maidRegistrationModel.getCnic());
        viewHolder.Address.setText(maidRegistrationModel.getAddress());
        viewHolder.Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ctx,Contact.class);
                ctx.startActivity(intent1);



            }
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maidlist2,parent,false);
        return new viewHolder(view);
    }

    class viewHolder extends RecyclerView.ViewHolder{
        TextView FulName,Mobile,CAtegory,Gender,Religion,Education,City,Age,Experience,Contact,Cnic,Address;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            FulName=itemView.findViewById(R.id.candname);
            CAtegory=itemView.findViewById(R.id.candcat);
            Mobile=itemView.findViewById(R.id.mobiletv);
            Gender=itemView.findViewById(R.id.gendertv);
            Religion=itemView.findViewById(R.id.religiontv);
            Education=itemView.findViewById(R.id.edutv);
            City=itemView.findViewById(R.id.citytv);
            Age=itemView.findViewById(R.id.agetv);
            Experience=itemView.findViewById(R.id.exptv);
            Contact = itemView.findViewById(R.id.contact);
            Cnic = itemView.findViewById(R.id.cnictv);
            Address = itemView.findViewById(R.id.addtv);


        }
    }
    }
