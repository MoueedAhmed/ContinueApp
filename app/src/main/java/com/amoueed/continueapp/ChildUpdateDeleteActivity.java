package com.amoueed.continueapp;

import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;

import com.amoueed.continueapp.Model.Child;

public class ChildUpdateDeleteActivity extends AppCompatActivity {
    private MaterialButton updateButton;
    private MaterialButton deleteButton;
    private TextInputEditText childName;
    private TextInputEditText childDOB;
    private Spinner genderSpinner;
    private TextInputEditText guardianName;
    private TextInputEditText phone;
    private TextInputEditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_update_delete);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        Child c = b.getParcelable("myChild");
        String mName = c.getChildName();
        String mDateOfBirth = c.getDateOfBirth();
        String mGender = c.getGender();
        String mGuardian = c.getGuardianName();
        String mPhone = c.getPhone();
        String mEmail = c.getEmail();

        childName = findViewById(R.id.childName);
        childDOB = findViewById(R.id.childDOB);
        genderSpinner = findViewById(R.id.genderSpinner);
        guardianName = findViewById(R.id.guardianName);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);

        childName.setText(mName);
        childDOB.setText(mDateOfBirth);
        genderSpinner.setSelection(setSpinner(mGender));
        guardianName.setText(mGuardian);
        phone.setText(mPhone);
        email.setText(mEmail);

        updateButton = findViewById(R.id.updateButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    int setSpinner(String gender){
        if(gender.equals("Male")){
            return 0;
        }else if(gender.equals("Female")){
            return 1;
        }
        else{
            return 2;
        }
    }
}
