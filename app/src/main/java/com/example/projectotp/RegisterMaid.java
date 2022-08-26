package com.example.projectotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterMaid extends AppCompatActivity {
EditText name,number,gender,religion,education,city,age,experience,cnic,address;
TextInputLayout Spinner, Spinnergender, edu;
AutoCompleteTextView autoCompleteTextView;
ArrayList<String> arrayList;
ArrayAdapter<String> arrayAdapter;

    AutoCompleteTextView autoCompleteTextView1;
    ArrayList<String> arrayList1;
    ArrayAdapter<String> arrayAdapter1;
int index;
TextView registermaid;
ProgressBar progressBar;
String FullName,Number,Category,Gender,Religion,Education,City,Age,Experience,Cnic,Address;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_maid);
        name = findViewById(R.id.fullname);
        number = findViewById(R.id.maidmob);
        getSupportActionBar().hide();
        religion = findViewById(R.id.maidrelgn);
        education = findViewById(R.id.education);
        city = findViewById(R.id.city);
        age = findViewById(R.id.age);
        cnic = findViewById(R.id.idcard);
        address = findViewById(R.id.address);
        experience = findViewById(R.id.experience);
        registermaid = findViewById(R.id.regmaid);
        progressBar = findViewById(R.id.probar);
        edu = findViewById(R.id.edu);

        Spinner = findViewById(R.id.spinner);
        autoCompleteTextView = findViewById(R.id.autocom);
        arrayList = new ArrayList<>();
        arrayList.add("Laundry");
        arrayList.add("PatientCare");
        arrayList.add("BabySitting");
        arrayList.add("KitchenCare");
        arrayList.add("HouseCleaning");
        arrayList.add("Gardener");
        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arrayList);
        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setThreshold(1);


        Spinnergender = findViewById(R.id.gendersspinner);
        autoCompleteTextView1 = findViewById(R.id.autocom1);
        arrayList1 = new ArrayList<>();
        arrayList1.add("Male");
        arrayList1.add("Female");
        arrayList1.add("Rather Not To Say");
        arrayAdapter1 = new ArrayAdapter<>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,arrayList1);
        autoCompleteTextView1.setAdapter(arrayAdapter1);
        autoCompleteTextView1.setThreshold(1);




        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Maids");

        

        registermaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullName = name.getText().toString().trim();
                Number = number.getText().toString().trim();
                Category = autoCompleteTextView.getText().toString();
                Gender = autoCompleteTextView1.getText().toString();
                Religion = religion.getText().toString().trim();
                Education = education.getText().toString().trim();
                City = city.getText().toString().trim();
                Age = age.getText().toString().trim();
                Experience = experience.getText().toString().trim();
                Cnic = cnic.getText().toString().trim();
                Address = address.getText().toString().trim();


                if (TextUtils.isEmpty(FullName)){
                    name.setError("Full Name is Required");
                    return;
                }

                else if (TextUtils.isEmpty(Number)){
                    number.setError("Number is Required");
                    return;
                }

                else if (TextUtils.isEmpty(Category)){
                    Spinner.setError("Category is Required");
                    return;
                }

                else if (TextUtils.isEmpty(Gender)){
                    gender.setError("Gender is Required");
                    return;
                }

                else if (TextUtils.isEmpty(Religion)){
                    religion.setError("Religion is Required");
                    return;
                }

                else if (TextUtils.isEmpty(Education)){
                    education.setError("Education is Required");
                    return;
                }

                else if (TextUtils.isEmpty(City)){
                    city.setError("City is Required");
                    return;
                }

                else if (TextUtils.isEmpty(Age)){
                    age.setError("Age is Required");
                    return;
                }

                else if (TextUtils.isEmpty(Experience)){
                    experience.setError("Experience is Required");
                    return;
                }

                else if (TextUtils.isEmpty(Cnic)){
                    cnic.setError("CNIC Number Is Necessary");
                    return;
                }

                else if (TextUtils.isEmpty(Address)){
                    address.setError("Address Must Be Entered");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                registermaid.setVisibility(View.INVISIBLE);

                //storing user data to firebase realtime database
                MaidRegistrationModel maidRegistrationModel = new MaidRegistrationModel(FullName,Number,Category,Gender,Religion,Education,City,Age,Experience,Cnic,Address);
                FirebaseDatabase.getInstance().getReference("Maids").push().setValue(maidRegistrationModel)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(RegisterMaid.this, "Maid Registered", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterMaid.this,AdminHome.class));
                                onBackPressed();


                            }
                        });





            }
        });
    }
}