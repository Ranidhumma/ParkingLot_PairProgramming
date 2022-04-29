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

    /**
     *
     * @param parkingLotMap
     * @return key for evenly distribution
     */
    public int getKeyToPark(Map<Integer, Vehicle> parkingLotMap) {
        for(Map.Entry map : parkingLotMap.entrySet()){
            if(map.getValue()==null) {
                this.key = (int) map.getKey();//finds the key where the lot is empty
                break;//breaks after finding evenly parked key
            }
        }
        return this.key;
    }
}
