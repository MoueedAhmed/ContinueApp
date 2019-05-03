package com.amoueed.continueapp.Model;

public class Vaccine {
    private String name;
    private String dueDate;
    private String givenDate;
    private String status;
    private String reminderStatus;
    private int imageResource;

    public Vaccine() {

    }

    public Vaccine(String name, String dueDate, String givenDate, String status, String reminderStatus, int imageResource) {
        this.name = name;
        this.dueDate = dueDate;
        this.givenDate = givenDate;
        this.status = status;
        this.reminderStatus = reminderStatus;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getGivenDate() {
        return givenDate;
    }

    public String getStatus() {
        return status;
    }

    public String getReminderStatus() {
        return reminderStatus;
    }

    public int getImageResource() {
        return imageResource;
    }
}
