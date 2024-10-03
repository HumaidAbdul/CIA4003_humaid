package com.example.pjaapp.model;

public class Student
{
    private String name;
    private String id;
    private double gpa;
    private boolean married;

    // no-arguement constructor
    public Student(){}

    // four arguement constructor
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

    public String toString()
    {
        return  "Name:    "+ name +
                "\nID:      "+ id   +
                "\nGPA:     "+ gpa  +
                "\nMarried: "+ married;
    }
}
