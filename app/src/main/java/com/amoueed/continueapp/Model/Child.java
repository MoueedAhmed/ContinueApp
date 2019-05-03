package com.amoueed.continueapp.Model;

import android.content.res.TypedArray;
import com.amoueed.continueapp.R;
import java.util.ArrayList;

public class Child {
    private String id;
    private String childName;
    private String dateOfBirth;
    private String gender;
    private ArrayList<Vaccine> mVaccineData;

    public Child(){

    }

    public Child(String id, String childName, String dateOfBirth, String gender, ArrayList<Vaccine> vaccineArrayList) {
        this.id = id;
        this.childName = childName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.mVaccineData = vaccineArrayList;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public ArrayList<Vaccine> getmVaccineData() {
        return mVaccineData;
    }

    public void setmVaccineData(ArrayList<Vaccine> mVaccineData) {
        this.mVaccineData = mVaccineData;
    }
}
