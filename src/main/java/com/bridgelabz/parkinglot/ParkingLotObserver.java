package com.bridgelabz.parkinglot;

public interface ParkingLotObserver {
    public void update(String message);
    public String getStatus();
}
