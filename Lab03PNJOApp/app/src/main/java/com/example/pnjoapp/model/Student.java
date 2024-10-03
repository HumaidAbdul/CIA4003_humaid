package com.example.pnjoapp.model;

public class Student
{
    private String name;
    private String id;
    private double gpa;
    private boolean married;

    private Car car;

    // no-arguement constructor
    public Student(){}

    // five argument constructor
    public Student(String name, String id, double gpa, boolean married, Car car)
    {
        this.name = name;
        this.id = id;
        this.gpa = gpa;
        this.married = married;
        this.car = car;
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return  "Name    =" + name           + "\n" +
                "ID      =" + id             + "\n" +
                "GPA     =" + gpa            + "\n" +
                "Married =" + married        + "\n\n" +
                "Car     =" + car.toString() + "\n";
    }
}
