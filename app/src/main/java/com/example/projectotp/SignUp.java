package com.example.projectotp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

public class SignUp  extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText personFullName, personEmailAddress, personPass, personConfPass, phoneNumber;
    CountryCodePicker countryCode;
    Button regsiterAccountBtn;
    Boolean isDataValid = false;
    FirebaseAuth fAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        personFullName = findViewById(R.id.registerFullName);
        personEmailAddress = findViewById(R.id.registerEmail);
        personPass = findViewById(R.id.regsiterPass);
        personConfPass = findViewById(R.id.retypePass);
        countryCode = findViewById(R.id.ccp);
        phoneNumber = findViewById(R.id.registerPhoneNumber);
        regsiterAccountBtn = findViewById(R.id.registerBtn);
        textView = findViewById(R.id.textlink);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserLogin.class));
            }
        });

        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

//        // validating the data
//        validateData(personFullName);
//        validateData(personEmailAddress);
//        validateData(personPass);
//        validateData(personConfPass);
//        validateData(phoneCountryCode);
//        validateData(phoneNumber);


        if (!personPass.getText().toString().equals(personConfPass.getText().toString())) {
            isDataValid = false;
            personConfPass.setError("Password Do not Match");
            return;
        } else {
            isDataValid = true;

        }

        regsiterAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = personFullName.getText().toString().trim();
                String Email = personEmailAddress.getText().toString().trim();
                String Pass = personPass.getText().toString().trim();
                String Cc = countryCode.getDefaultCountryCode().toString().trim();
                String Phone = phoneNumber.getText().toString().trim();


                if (TextUtils.isEmpty(Name)){
                    personFullName.setError("Error");
                    return;
                }
                if (TextUtils.isEmpty(Email)){
                    personEmailAddress.setError("error");
                    return;
                }
                if (TextUtils.isEmpty(Pass)){
                    personPass.setError("error");
                    return;
                }

                if (TextUtils.isEmpty(Phone)){
                    phoneNumber.setError("error");
                    return;
                }

                // proceed with the registration of the user
                if (isDataValid) {
                    fAuth.createUserWithEmailAndPassword(Email, Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(SignUp.this, "User Account is Created.", Toast.LENGTH_SHORT).show();

                            // send the user to verify the phone
                            Intent phone = new Intent(SignUp.this, manageotp.class);

                            phone.putExtra("phone", "+" + countryCode.getDefaultCountryCode().toString() + phoneNumber.getText().toString());
                            Model model = new Model(Name,Email,Pass,Cc,Phone);
                            FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(model)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                        }
                                    });
                            startActivity(phone);
                            Log.d(TAG, "onSuccess: " + "+" + countryCode.getDefaultCountryCode().toString() + phoneNumber.getText().toString());
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUp.this, "Error !" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });


        //   public void validateData(EditText field){
//        if(field.getText().toString().isEmpty()){
//            isDataValid = false;
//            field.setError("Required Field.");
//
//        }else {
//            isDataValid = true;
//
//        }
//    }

    }
    }

