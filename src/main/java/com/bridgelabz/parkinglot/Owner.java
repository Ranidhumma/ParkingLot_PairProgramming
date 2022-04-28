package com.bridgelabz.parkinglot;

public class Owner implements ParkingLotObserver {
    private static String status;

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
