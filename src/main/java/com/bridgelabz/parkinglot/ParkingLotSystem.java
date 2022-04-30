package com.bridgelabz.parkinglot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author -> Karthik M C,RaniDhumma
 * @since -> 25/04/2022
 */
public class ParkingLotSystem {
    public final int MAX_PARKING_CAPACITY = 10;
    private Map<Integer, Vehicle> parkingLotMap = new LinkedHashMap<>();
    private Map<Integer, Vehicle> parkingLotMap1 = new LinkedHashMap<>();
    private Map<Integer, Vehicle> parkingLotMap2 = new LinkedHashMap<>();


    List<ParkingLotObserver> observers;
    Attendant attendant = new Attendant();
    private LocalDateTime time;


    public ParkingLotSystem() {
        observers = new ArrayList<>();
        for (int i = 1; i <= MAX_PARKING_CAPACITY; i++) {
            parkingLotMap1.put(i, null);
        }
        for (int i = 1; i <= MAX_PARKING_CAPACITY; i++) {
            parkingLotMap2.put(i, null);
        }
    }

    /**
     * @param vehicle
     * @throws ParkingLotException when parking lot is full
     */
    public void park(Vehicle vehicle, DriverType driverType, CarType carType) throws ParkingLotException {
        if (carType == CarType.SMALL) parkingLotMap = parkingLotMap1;
        if (carType == CarType.LARGE) parkingLotMap = parkingLotMap2;
        if (this.parkingLotMap.containsValue(vehicle))
            throw new ParkingLotException("vehicle is already there");
        if (this.parkingLotMap.size() == MAX_PARKING_CAPACITY && !this.parkingLotMap.containsValue(null))
            throw new ParkingLotException("parking Lot is Full");
        if (this.parkingLotMap.containsValue(null)) {
            int key = attendant.parkThevehicle(this.parkingLotMap, driverType);
            this.parkingLotMap.put(key, vehicle);
            if (this.parkingLotMap.size() > MAX_PARKING_CAPACITY) {
                this.parkingLotMap.put(key, null);
                throw new ParkingLotException("parking Lot size is outOfBound");
            }
            LocalDateTime localDateTime = LocalDateTime.now();
            setParkedTime(localDateTime);
        }
        if (this.parkingLotMap.size() == MAX_PARKING_CAPACITY && !this.parkingLotMap.containsValue(null)) {
            for (ParkingLotObserver observer : observers) {
                if(parkingLotMap.equals(parkingLotMap1))//check for parkinglot1
                observer.update("ParkingLot 1 is Full");
                else if (parkingLotMap.equals(parkingLotMap2)) //check for parkinglot2
                    observer.update("ParkingLot 2 is Full");
            }
        }
    }


    /**
     * @param time
     */
    public void setParkedTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * @return Local time
     */
    public LocalDateTime getParkedTime() {
        return this.time;
    }

    /**
     * @param vehicle
     * @return if vehicle is parked return true else return false
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        if (this.parkingLotMap.containsValue(vehicle)) return true;
        return false;
    }

    /**
     * @param vehicle
     * @throws ParkingLotException if parking lot is empty and
     *                             asked for incorrect vehicle
     */
    public void unPark(Vehicle vehicle) throws ParkingLotException {
        if (parkingLotMap1.containsValue(vehicle))
            parkingLotMap = parkingLotMap1;
        if (parkingLotMap2.containsValue(vehicle))
            parkingLotMap = parkingLotMap2;
        Integer key = 0;
        int nullCount = 0;
        for (Map.Entry map : parkingLotMap.entrySet()) {
            if (map.getValue() == null)
                nullCount++;
        }
        if (nullCount == MAX_PARKING_CAPACITY) throw new ParkingLotException("parking lot is empty");
        if (this.parkingLotMap.containsValue(vehicle)) {
            for (Map.Entry map : parkingLotMap.entrySet()) {
                if (map.getValue() == vehicle) {
                    key = (Integer) map.getKey();
                }
            }
            this.parkingLotMap.put(key, null);
            if (this.parkingLotMap1.containsValue(null))
                for (ParkingLotObserver observer : observers) {
                    observer.update("Parkinglot1 has space");
                }
            else if (parkingLotMap2.containsValue(null))
                for (ParkingLotObserver observer : observers) {
                    observer.update("Parkinglot2 has space");
                }
            if (parkingLotMap1.containsValue(null) && parkingLotMap2.containsValue(null))
                for (ParkingLotObserver observer : observers) {
                    observer.update("Both Parkinglot has space");
                }
            return;
        }
        throw new ParkingLotException("Ask for correct vehicle");
    }


    /**
     * @param vehicle
     * @return true if parking lot is empty return true else return false
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        if (!this.parkingLotMap.containsValue(vehicle)) return true;//checking for vehicle is unparked
        return false;
    }

    public void registerObservers(ParkingLotObserver observer) {
        this.observers.add(observer);
    }


    /**
     * @param vehicle
     * @return vehicle location in the parking lot
     */
    public int getVehicleLoacation(Vehicle vehicle) {
        if (parkingLotMap1.containsValue(vehicle))
            parkingLotMap = parkingLotMap1;//checking for vehicle availablity in particular lot
        if (parkingLotMap2.containsValue(vehicle)) parkingLotMap = parkingLotMap2;
        for (Map.Entry map : parkingLotMap.entrySet()) {
            if (map.getValue() == vehicle)
                return (int) map.getKey();
        }
        return 0;
    }


    public int getParkingMapSize() {
        return parkingLotMap.size();
    }
}
