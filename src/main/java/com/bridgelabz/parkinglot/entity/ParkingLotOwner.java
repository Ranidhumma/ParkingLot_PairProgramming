package com.bridgelabz.parkinglot.entity;

import com.bridgelabz.parkinglot.ParkingLotObserver;

import java.util.Map;

public class ParkingLotOwner implements ParkingLotObserver {
    private static String status;
    private int key;
    private int lotNumber;

    /**
     *
     * @param message updating message to owner
     */
    public void update(String message) {
        this.status = message;
    }

    /**
     *
     * @return updated message
     */
    public String getStatus() {
        return this.status;
    }


}
