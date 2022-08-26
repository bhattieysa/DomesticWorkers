package com.example.projectotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
EditText editText1, editText2, editText3, editText4;
TextView textView;
String S1, S2, S3, S4;
ProgressDialog progressDialog;
ConstraintLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        progressDialog = new ProgressDialog(Payment.this);
        progressDialog.setMessage("Processing...");


        editText1 = findViewById(R.id.cardholdername);
        editText2 = findViewById(R.id.cardnum);
        editText3 = findViewById(R.id.date);
        editText4 = findViewById(R.id.cvv);
        layout = findViewById(R.id.constraintLayout);

        textView = findViewById(R.id.paynow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                S1 = editText1.getText().toString();
                S2 = editText2.getText().toString();
                S3 = editText3.getText().toString();
                S4 = editText4.getText().toString();


                if (TextUtils.isEmpty(S1)) {
                    editText1.setError("Enter Card Holder Name");
                    return;
                }

                if (TextUtils.isEmpty(S2)) {
                    editText2.setError("Enter Card Number");
                    return;
                }

                if (TextUtils.isEmpty(S3)) {
                    editText3.setError("Enter Correct Date");
                    return;
                }

                if (TextUtils.isEmpty(S4)) {
                    editText4.setError("Enter Correct VCC");
                    return;
                }

                progressDialog.show();
                Thread time = new Thread(){
                    @Override
                    public void run() {
                        try {
                            sleep(7000);
                            startActivity(new Intent(getApplicationContext(),Success.class));
                            progressDialog.dismiss();
                            finish();
                            super.run();
                        }
                        catch (InterruptedException exception){
                            exception.printStackTrace();
                        }


                    }
                };
                time.start();






            }
        });
    }

}
