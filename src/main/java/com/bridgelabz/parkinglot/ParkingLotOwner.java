package com.bridgelabz.parkinglot;

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

    public int getKeyToPark(Map<Integer, Vehicle> parkingLotMap) {
        lotNumber=1;
        if(parkingLotMap.isEmpty()) this.key=lotNumber;
        for(Map.Entry map : parkingLotMap.entrySet()){
            lotNumber++;
        }
        this.key=lotNumber;
        return this.key;
    }
}
