package com.bridgelabz.parkinglot;

import java.util.Map;

public class Attendant {
    ParkingLotOwner owner = new ParkingLotOwner();


    public int parkThevehicle(Map<Integer, Vehicle> parkingLotMap) {
        return owner.getKeyToPark(parkingLotMap);

    }
}
