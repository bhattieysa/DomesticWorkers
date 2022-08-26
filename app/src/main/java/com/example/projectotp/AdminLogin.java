package com.example.projectotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLogin extends AppCompatActivity {
EditText Email,Pasword;
TextView adminlogin;
String email,password;
ProgressBar progressBar;
FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Email = findViewById(R.id.adminemail);
        Pasword = findViewById(R.id.adminpass);
        progressBar = findViewById(R.id.loginbar);
        adminlogin = findViewById(R.id.adminlogin);
        getSupportActionBar().hide();
        fAuth = FirebaseAuth.getInstance();
        adminlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = Email.getText().toString();
                password = Pasword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Email.setError("Enter E-mail");
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    Pasword.setError("Enter Password");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                adminlogin.setVisibility(View.GONE);



                fAuth.signInWithEmailAndPassword(email, password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()){
                                   Toast.makeText(AdminLogin.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                                   FirebaseUser user = fAuth.getCurrentUser();
                                   updateUI(user);
                                   startActivity(new Intent(getApplicationContext(),AdminHome.class));
                                   finish();
                               } else {
                                   Toast.makeText(AdminLogin.this, "Try Again! Login Failed", Toast.LENGTH_SHORT).show();
                                   progressBar.setVisibility(View.INVISIBLE);
                                   adminlogin.setVisibility(View.VISIBLE);
                                   updateUI(null);
                               }
                           }
                       });

            }
        });
    }


    // USER session
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = fAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void updateUI(FirebaseUser user) {

    }


}