package com.amoueed.continueapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private MaterialButton cancelButtonRegistration;
    private TextView registrationTextViewRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextInputEditText numberEditTextRegistration;
    private TextInputEditText passwordEditRegistration;
    private MaterialButton signUpButtonRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        cancelButtonRegistration = findViewById(R.id.cancelButtonRegistration);
        cancelButtonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        registrationTextViewRegistration = findViewById(R.id.registrationTextViewRegistration);
        registrationTextViewRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
        }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        numberEditTextRegistration = findViewById(R.id.numberEditTextRegistration);
        passwordEditRegistration = findViewById(R.id.passwordEditRegistration);
        signUpButtonRegistration = findViewById(R.id.signUpButtonRegistration);
        signUpButtonRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = numberEditTextRegistration.getText().toString().trim();
                String mPassword = passwordEditRegistration.getText().toString().trim();
                if(TextUtils.isEmpty(mEmail)){
                    numberEditTextRegistration.setError("Required!");
                    return;
                }
                if(TextUtils.isEmpty(mPassword)){
                    passwordEditRegistration.setError("Required!");
                    return;
                }
                progressDialog.setMessage("Processing...");
                progressDialog.show();

                mEmail = mEmail + "@amoueed.com";

                firebaseAuth.createUserWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Sucessfull",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),ChildReadActivity.class));
                            progressDialog.dismiss();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Problem in Auth",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    }
                });
            }
        });
    }
}
