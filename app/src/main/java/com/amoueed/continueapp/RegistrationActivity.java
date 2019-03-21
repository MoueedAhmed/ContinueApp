package com.amoueed.continueapp;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {
    MaterialButton cancelButtonRegistration;
    TextView registrationTextViewRegistration;
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
    }
}
