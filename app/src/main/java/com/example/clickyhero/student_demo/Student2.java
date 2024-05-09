package com.example.clickyhero.student_demo;

public class Student2 {

    String studentID;
    String firstName;
    String lastName;
    int gender;
    String city;

    public Student2() {
    }

    public Student2(String firstName, int gender) {
        this.firstName = firstName;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

}
