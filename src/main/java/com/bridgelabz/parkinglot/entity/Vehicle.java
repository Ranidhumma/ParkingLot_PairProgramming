package com.bridgelabz.parkinglot.entity;

public class Vehicle {
    String numPlate;
    int id;
    String modelName;
    String color;

    public Vehicle(int id, String name) {
        this.id = id;
        this.modelName = name;
    }

    public Vehicle(int id, String name, String color) {
        this.id = id;
        this.modelName = name;
        this.color = color;
    }

    public Vehicle(int id, String name, String color, String numPlate) {
        this.id = id;
        this.modelName = name;
        this.color = color;
        this.numPlate = numPlate;
    }

    public String getColor() {
        return color;
    }

    public String getModelName() {
        return modelName;
    }

    public String getNumPlate() {
        return numPlate;
    }
}
