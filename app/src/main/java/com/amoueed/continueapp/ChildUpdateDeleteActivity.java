package com.amoueed.continueapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.amoueed.continueapp.Model.Child;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ChildUpdateDeleteActivity extends AppCompatActivity {
    private MaterialButton updateButton;
    private MaterialButton deleteButton;
    private TextInputEditText childName;
    private TextInputEditText childDOB;
    private Spinner genderSpinner;
    private TextInputEditText guardianName;
    private TextInputEditText phone;
    private TextInputEditText email;

    static final int DOB_DIALOG_ID = 900;
    private int year;
    private int month;
    private int day;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_update_delete);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Child").child(uId);

        Intent i = getIntent();
        Bundle b = i.getExtras();
        Child c = b.getParcelable("myChild");
        final String mId = c.getId();
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
                String mChildName = childName.getText().toString().trim();
                String mDateOfBirth = childDOB.getText().toString().trim();
                String mGender = genderSpinner.getSelectedItem().toString().trim();
                String mGuardianName = guardianName.getText().toString().trim();
                String mPhone = phone.getText().toString().trim();
                String mEmail = email.getText().toString().trim();

                if(TextUtils.isEmpty(mChildName)){
                    childName.setError("Required!");
                    return;
                }
                if(TextUtils.isEmpty(mDateOfBirth)){
                    childDOB.setError("Required!");
                    return;
                }
                if(TextUtils.isEmpty(mGuardianName)){
                    guardianName.setError("Required!");
                    return;
                }
                if(TextUtils.isEmpty(mPhone)){
                    phone.setError("Required!");
                    return;
                }
                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Required!");
                    return;
                }

                Child child = new Child(mId, mChildName, mDateOfBirth, mGender, mGuardianName, mPhone, mEmail);
                databaseReference.child(mId).setValue(child);
                Toast.makeText(getApplicationContext(),"Data Updated",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(mId).removeValue();
                Toast.makeText(getApplicationContext(),"Deleted",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        childDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DOB_DIALOG_ID);
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


    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into TextInputEditText childDOBEditText
            childDOB.setText(new StringBuilder().append(day)
                    .append("/").append(displayTheMonthInCharacters(month)).append("/").append(year)
                    .append(" "));
        }
    };

    //Displays a new dialog for date picker
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DOB_DIALOG_ID:
                return new DatePickerDialog(this,
                        datePickerListener, year, month,day);
        }
        return null;
    }

    //Returns the Month Abbreviation for the corresponding number.
    private String displayTheMonthInCharacters(int passedMonth){
        switch(passedMonth){
            case 0:
                return "Jan";
            case 1:
                return"Feb";
            case 2:
                return"Mar";
            case 3:
                return"Apr";
            case 4:
                return"May";
            case 5:
                return"Jun";
            case 6:
                return"Jul";
            case 7:
                return"Aug";
            case 8:
                return"Sept";
            case 9:
                return"Oct";
            case 10:
                return"Nov";
            case 11:
                return"Dec";

        }
        return "";
    }
}
