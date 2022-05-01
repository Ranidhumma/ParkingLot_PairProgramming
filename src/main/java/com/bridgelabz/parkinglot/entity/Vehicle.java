package com.bridgelabz.parkinglot.entity;

public class Vehicle {
    int id;
    String name;
    String color;

    public Vehicle(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Vehicle(int id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
