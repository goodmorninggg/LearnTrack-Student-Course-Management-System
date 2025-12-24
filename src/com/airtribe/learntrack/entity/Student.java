package com.airtribe.learntrack.entity;

public class Student extends Person {
    private String batch;
    private boolean active;

    public Student() {
        super();
    }

    public Student(String id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    // Constructor overloading: without email
    public Student(String id, String firstName, String lastName, String batch, boolean active) {
        super(id, firstName, lastName, "N/A");
        this.batch = batch;
        this.active = active;
    }

    public String getBatch() {
        return batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getFullName() {
        return super.getFullName() + " (" + batch + ")";
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + getId() + '\'' +
                ", name='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", batch='" + batch + '\'' +
                ", active=" + active +
                '}';
    }
}
