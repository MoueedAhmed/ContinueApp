package com.amoueed.continueapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private MaterialButton loginButtonLogin;
    private TextView registrationTextViewLogin;
    private TextInputEditText numberEditTextLogin;
    private  TextInputEditText passwordEditTextLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registrationTextViewLogin = findViewById(R.id.registrationTextViewLogin);
        registrationTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
            }
        });

        numberEditTextLogin = findViewById(R.id.numberEditTextLogin);
        passwordEditTextLogin = findViewById(R.id.passwordEditTextLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        loginButtonLogin = findViewById(R.id.loginButtonLogin);
        loginButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mEmail = numberEditTextLogin.getText().toString().trim();
                String mPassword = passwordEditTextLogin.getText().toString().trim();
                if(TextUtils.isEmpty(mEmail)){
                    numberEditTextLogin.setError("Required!");
                    return;
                }
                if(TextUtils.isEmpty(mPassword)){
                    passwordEditTextLogin.setError("Required!");
                    return;
                }
                progressDialog.setMessage("Processing...");
                progressDialog.show();

                mEmail = mEmail + "@amoueed.com";

                firebaseAuth.signInWithEmailAndPassword(mEmail,mPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
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
