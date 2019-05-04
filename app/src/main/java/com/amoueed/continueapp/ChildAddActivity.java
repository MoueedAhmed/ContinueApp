package com.amoueed.continueapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.res.TypedArray;
import android.support.design.button.MaterialButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.amoueed.continueapp.model.Child;
import com.amoueed.continueapp.model.Vaccine;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class ChildAddActivity extends AppCompatActivity {

    private TextInputEditText childNameChildAdd;
    private TextInputEditText childDOBEditText;
    private Spinner genderSpinnerChildAdd;
    private MaterialButton addChildButtonChildAdd;
    private MaterialButton cancelButtonChildAdd;

    static final int DOB_DIALOG_ID = 999;
    private int year;
    private int month;
    private int day;

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private ArrayList<Vaccine> mVaccineData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_add);

        mVaccineData = new ArrayList<>();
        initializeData();

        childNameChildAdd = findViewById(R.id.childNameChildAdd);
        childDOBEditText = findViewById(R.id.childDOBChildAdd);
        childDOBEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DOB_DIALOG_ID);
            }
        });
        genderSpinnerChildAdd = findViewById(R.id.genderSpinnerChildAdd);
        cancelButtonChildAdd  = findViewById(R.id.cancelButtonChildAdd);
        addChildButtonChildAdd = findViewById(R.id.addChildButtonChildAdd);
        addChildButtonChildAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = databaseReference.push().getKey();
                String childName = childNameChildAdd.getText().toString().trim();
                String dateOfBirth = childDOBEditText.getText().toString().trim();
                String gender = genderSpinnerChildAdd.getSelectedItem().toString().trim();

                if(TextUtils.isEmpty(childName)){
                    childNameChildAdd.setError("Required!");
                    return;
                }
                if(TextUtils.isEmpty(dateOfBirth)){
                    childDOBEditText.setError("Required!");
                    return;
                }

                Child child = new Child(id, childName, dateOfBirth, gender, mVaccineData);
                databaseReference.child(id).setValue(child);
                Toast.makeText(getApplicationContext(),"Data Inserted",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        cancelButtonChildAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        setCurrentDate();

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String uId = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Child").child(uId);
    }

    private void setCurrentDate() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // set current date into textview
        childDOBEditText.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(day).append("/").append(displayTheMonthInCharacters(month)).append("/")
                .append(year).append(" "));
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

    private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into TextInputEditText childDOBEditText
            childDOBEditText.setText(new StringBuilder().append(day)
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

    private void initializeData() {
        // Get the resources from the XML file.
        String[] vaccineList = getResources().getStringArray(R.array.vaccine_names);
        TypedArray vaccinesImageResources = getResources().obtainTypedArray(R.array.vaccine_images);

        // Clear the existing data (to avoid duplication).
        mVaccineData.clear();

        for(int i=0;i<vaccineList.length;i++){
            mVaccineData.add(new Vaccine(vaccineList[i], "Due Date: xx:xx:xxxx","Given Date: xx:xx:xxxx",
                    "Status: Not Specified", "Reminder: Yes", vaccinesImageResources.getResourceId(i,0)));
        }

        vaccinesImageResources.recycle();
    }
}
