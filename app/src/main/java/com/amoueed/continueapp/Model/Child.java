package com.amoueed.continueapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Child implements Parcelable {
    String id;
    String childName;
    String dateOfBirth;
    String gender;
    String guardianName;
    String phone;
    String email;

    public Child(){

    }

    public Child(String id, String childName, String dateOfBirth, String gender, String guardianName, String phone, String email) {
        this.id = id;
        this.childName = childName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.guardianName = guardianName;
        this.phone = phone;
        this.email = email;
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

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Child(Parcel p)
    {
        id = p.readString();
        childName = p.readString();
        dateOfBirth = p.readString();
        gender = p.readString();
        guardianName = p.readString();
        phone = p.readString();
        email = p.readString();
    }

    @Override
    public int describeContents()
    {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(id);
        dest.writeString(childName);
        dest.writeString(dateOfBirth);
        dest.writeString(gender);
        dest.writeString(guardianName);
        dest.writeString(phone);
        dest.writeString(email);
    }

    // We need to add a Creator
    public static final Parcelable.Creator<Child> CREATOR = new Parcelable.Creator<Child>() {

        @Override
        public Child createFromParcel(Parcel parcel) {
            return new Child(parcel);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };
}
