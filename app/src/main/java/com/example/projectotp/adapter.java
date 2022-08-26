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

public class adapter extends FirebaseRecyclerAdapter<MaidRegistrationModel,adapter.myviewHolder>
{

   FirebaseRecyclerOptions<MaidRegistrationModel> options;
    Context ctx;

    public adapter(@NonNull  FirebaseRecyclerOptions<MaidRegistrationModel> options1, Context ctx) {
        super(options1);
        this.options = options1;
        this.ctx = ctx;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewHolder myviewHolder, int i, @NonNull MaidRegistrationModel maidRegistrationModel) {
        myviewHolder.FulName.setText(maidRegistrationModel.getFullName());
        myviewHolder.Mobile.setText(maidRegistrationModel.getNumber());
        myviewHolder.CAtegory.setText(maidRegistrationModel.getCategory());
        myviewHolder.Gender.setText(maidRegistrationModel.getGender());
        myviewHolder.Age.setText(maidRegistrationModel.getAge());
        myviewHolder.City.setText(maidRegistrationModel.getCity());
        myviewHolder.Experience.setText(maidRegistrationModel.getExperience());
        myviewHolder.Religion.setText(maidRegistrationModel.getReligion());
        myviewHolder.Cnic.setText(maidRegistrationModel.getCnic());
        myviewHolder.Address.setText(maidRegistrationModel.getAddress());
        myviewHolder.Contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ctx,Contact.class);
                ctx.startActivity(intent1);



            }
        });



    }

    @NonNull
    @Override
    public myviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.maidlist,parent,false);
        return new myviewHolder(view);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getRef().removeValue();
    }

    class myviewHolder extends RecyclerView.ViewHolder{

        TextView FulName,Mobile,CAtegory,Gender,Religion,City,Age,Experience,Contact,Cnic,Address;
        String number;
        public myviewHolder(@NonNull View itemView) {
            super(itemView);


            FulName=itemView.findViewById(R.id.candname);
            CAtegory=itemView.findViewById(R.id.candcat);
            Mobile=itemView.findViewById(R.id.mobiletv);
            Gender=itemView.findViewById(R.id.gendertv);
            Religion=itemView.findViewById(R.id.religiontv);
            City=itemView.findViewById(R.id.citytv);
            Age=itemView.findViewById(R.id.agetv);
            Experience=itemView.findViewById(R.id.exptv);
            Contact = itemView.findViewById(R.id.contact);
            Cnic = itemView.findViewById(R.id.cnictv);
            Address = itemView.findViewById(R.id.addtv);


        }
    }
}
