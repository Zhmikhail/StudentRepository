package com.example.demo.entity;

import java.util.Date;

public class UserEntity {
    private int id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private int positionId;
    private String position;
    private double salary;
    private Date hireDate;
    private Date fireDate;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {this.positionId = positionId;}

    public void setPosition(String position) {this.position = position;}

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Date getFireDate() {
        return fireDate;
    }

    public void setFireDate(Date fireDate) {
        this.fireDate = fireDate;
    }
}