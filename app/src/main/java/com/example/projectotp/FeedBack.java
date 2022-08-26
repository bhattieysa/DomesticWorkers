package com.example.projectotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedBack extends AppCompatActivity {
EditText fb;
TextView submmit,showRating;
String FeedBack,rateString,saveString;
RatingBar ratingBar;
float rateValue;
DatabaseReference databaseReference;
FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        fb = findViewById(R.id.fbet);
        showRating = findViewById(R.id.showRating);
        ratingBar = findViewById(R.id.ratingbar);
        getSupportActionBar().hide();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("FeedBacks");

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();

                if (rateValue<=1 && rateValue>0){
                    showRating.setText("Very Bad " + rateValue);
                    return;
                }
                if (rateValue<=2 && rateValue>1){
                    showRating.setText("Bad " + rateValue);
                    return;
                }
                if (rateValue<=3 && rateValue>2){
                    showRating.setText("Good " + rateValue);
                    return;
                }
                if (rateValue<=4 && rateValue>3){
                    showRating.setText("Very Good " + rateValue);
                    return;
                }
                if (rateValue<=5 && rateValue>4){
                    showRating.setText("Sepurb " + rateValue);
                    return;
                }

            }
        });

        submmit = findViewById(R.id.submitbtn);
        submmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedBack = fb.getText().toString();
                rateString = String.valueOf(rateValue);
                saveString = FeedBack + rateString;
                fb.setText("");
                ratingBar.setRating(0);
                if (TextUtils.isEmpty(FeedBack)){
                    fb.setError("PLease Give Your FeedBack");
                    return;
                }

                feedbackModel feedbackModel = new feedbackModel(saveString);
                FirebaseDatabase.getInstance().getReference("FeedBacks").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(feedbackModel)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(FeedBack.this, "ThankYou For Your FeedBack", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(FeedBack.this,UserHome.class));


                            }
                        });

            }

        });

    }
}