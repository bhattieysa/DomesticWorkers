package com.example.projectotp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hbb20.CountryCodePicker;

public class UserLogin extends AppCompatActivity {
    EditText Email,Password,Phone;
    CountryCodePicker ccp;
    TextInputLayout textInputLayout;
    Button mLoginBtn,pLogin,showLogin, showLogin2;
    TextView mCreateBtn,forgotTextLink, textView;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    int clickCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Email = findViewById(R.id.loginEmail);
        Password = findViewById(R.id.editTextTextPassword);
        Phone = findViewById(R.id.loginPhoneNumber);
        ccp = findViewById(R.id.loginCC);
        ccp.registerCarrierNumberEditText(Phone);
        pLogin = findViewById(R.id.lohinBtnPhone);
        textInputLayout = findViewById(R.id.four);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);
        showLogin2 = findViewById(R.id.loginWithPhone2);
        showLogin =findViewById(R.id.loginWithPhone);
        showLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ccp.setVisibility(View.VISIBLE);
                    textInputLayout.setVisibility(View.VISIBLE);
                    pLogin.setVisibility(View.VISIBLE);
                    showLogin2.setVisibility(View.VISIBLE);
                    showLogin.setVisibility(View.INVISIBLE);
                    Password.setVisibility(View.VISIBLE);
            }
        });
        showLogin2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ccp.setVisibility(View.INVISIBLE);
                textInputLayout.setVisibility(View.INVISIBLE);
                pLogin.setVisibility(View.INVISIBLE);
                showLogin2.setVisibility(View.INVISIBLE);
                showLogin.setVisibility(View.VISIBLE);
            }
        });

        pLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserLogin.this, LoginOtp.class);
                intent.putExtra("mobile", ccp.getFullNumberWithPlus().replace(" ", ""));
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });


        textView = findViewById(R.id.textlink);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp.class));
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Email.setError("Email is Required.");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Password.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    Password.setError("Password Must be >= 6 Characters");
                    return;
                }


                // authenticate the user

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UserLogin.this, "Success", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UserLogin.this,UserHome.class));

                        }else {
                            Toast.makeText(UserLogin.this, "Error."+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });
    }
    //Checking if the user is already signed in or not
    FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Intent intent = new Intent(UserLogin.this, UserHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        fAuth.addAuthStateListener(authStateListener);
    }
    }
