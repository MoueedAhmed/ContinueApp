package com.amoueed.continueapp.model;

public class Vaccine {
    private String name;
    private String dueDate;
    private String givenDate;
    private String status;
    private String reminderStatus;

    public Vaccine() {

    }

    public Vaccine(String name, String dueDate, String givenDate, String status, String reminderStatus) {
        this.name = name;
        this.dueDate = dueDate;
        this.givenDate = givenDate;
        this.status = status;
        this.reminderStatus = reminderStatus;
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

}
