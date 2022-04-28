package com.bridgelabz.parkinglot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author -> Karthik M C,RaniDhumma
 * @since -> 25/04/2022
 */
public class ParkingLotSystem {
  private static final int MAX_PARKING_CAPACITY = 4;
  private Map<Integer,Vehicle> parkingLotMap ;

  List<ParkingLotObserver> observers;
  Attendant attendant = new Attendant();


  public ParkingLotSystem() {
    observers =new ArrayList<>();
    parkingLotMap = new LinkedHashMap<>();
  }

  /**
   *
   * @param vehicle
   * @throws ParkingLotException when parking lot is full
   */
  public void park(Vehicle vehicle) throws ParkingLotException {
    if(this.parkingLotMap.containsValue(vehicle))
      throw new ParkingLotException("vehicle is already there");

    if (this.parkingLotMap.size()==MAX_PARKING_CAPACITY)
      throw new ParkingLotException("parking Lot is Full");

    if(parkingLotMap.size()<MAX_PARKING_CAPACITY) {
      int key = attendant.parkThevehicle(parkingLotMap);
      this.parkingLotMap.put(key, vehicle);
    }

    if(this.parkingLotMap.size()==MAX_PARKING_CAPACITY){
      String message = "Parking Lot is Full";
      for(ParkingLotObserver observer:observers){
        observer.update(message);
      }
//      owner.update(message);
//      securityPersonnel.update(message);

    }
  }

  /**
   *
   * @param vehicle
   * @return if vehicle is parked return true else return false
   */
  public boolean isVehicleParked(Vehicle vehicle) {
    if(this.parkingLotMap.containsValue(vehicle)) return true;
    return false;
  }

  /**
   *
   * @param vehicle
   * @throws ParkingLotException if parking lot is empty and
   * asked for incorrect vehicle
   */
  public void unPark(Vehicle vehicle) throws ParkingLotException {
    Integer key=0;
    if(this.parkingLotMap.isEmpty()) throw new ParkingLotException("parking lot is empty");
    if(this.parkingLotMap.containsValue(vehicle)) {
      for(Map.Entry map : parkingLotMap.entrySet()){
        if(map.getValue()==vehicle){
          key= (Integer) map.getKey();
        }
      }
      this.parkingLotMap.remove(key);
      if(this.parkingLotMap.size()<MAX_PARKING_CAPACITY) {
        for(ParkingLotObserver observer:observers){
          observer.update("Parkinglot has space");
        }

      }
      return;
    }
    throw new ParkingLotException("Ask for correct vehicle");
  }

  /**
   *
   * @param vehicle
   * @return true if parking lot is empty return true else return false
   */
  public boolean isVehicleUnParked(Vehicle vehicle) {
    if(!this.parkingLotMap.containsValue(vehicle)) return true;//checking for vehicle is unparked
    return false;
  }

  public void registerObservers(ParkingLotObserver observer) {
    this.observers.add(observer);
  }

  public int getVehicleLotNumber(Vehicle vehicle) {
    for(Map.Entry map : parkingLotMap.entrySet()){
      if(map.getValue()==vehicle)
        return (int) map.getKey();
    }
    return 0;
  }
}
