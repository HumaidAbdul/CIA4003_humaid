package com.example.psjoapp.model;

public class Student
{
    // Attributes for the student class
    private String name;
    private String id;
    private double gpa;
    private boolean married;

    // No-argument constructor
    public Student(){}

    // Four-argument constructor
    public Student(String name, String id, double gpa, boolean married)
    {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
        this.married = married;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Name=" + name + "\n" +
                "ID =" + id   + "\n" +
                "GPA=" + gpa  + "\n" +
                "Married=" + married;
    }
}
