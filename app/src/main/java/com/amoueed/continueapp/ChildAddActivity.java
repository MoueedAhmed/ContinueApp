package com.amoueed.continueapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

public class ChildAddActivity extends AppCompatActivity {
    TextInputEditText childDOBEditText;
    static final int DOB_DIALOG_ID = 999;
    private int year;
    private int month;
    private int day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_add);

        childDOBEditText = findViewById(R.id.childDOBChildAdd);
        childDOBEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DOB_DIALOG_ID);
            }
        });
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
            childDOBEditText.setText(new StringBuilder().append(month + 1)
                    .append("-").append(day).append("-").append(year)
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
}
