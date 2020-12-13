package com.example.week4_code_challenge_4.model;

/**
 * The POJO class asociated to the Item element
 */
public class Student {
    private final String firstName;
    private final String lastName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
