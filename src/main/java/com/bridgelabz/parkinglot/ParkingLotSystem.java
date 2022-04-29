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
  private static final int MAX_PARKING_CAPACITY = 4;
  private Map<Integer,Vehicle> parkingLotMap = new LinkedHashMap<>(); ;

  List<ParkingLotObserver> observers;
  Attendant attendant = new Attendant();
  private LocalDateTime time;



  public ParkingLotSystem() {
    observers =new ArrayList<>();
    for(int i=1;i<=MAX_PARKING_CAPACITY;i++){
      parkingLotMap.put(i,null);
    }
  }

  /**
   *
   * @param vehicle
   * @throws ParkingLotException when parking lot is full
   */
  public void park(Vehicle vehicle) throws ParkingLotException {
    if(this.parkingLotMap.containsValue(vehicle))
      throw new ParkingLotException("vehicle is already there");
    if (this.parkingLotMap.size()==MAX_PARKING_CAPACITY && !parkingLotMap.containsValue(null))
      throw new ParkingLotException("parking Lot is Full");
    if(this.parkingLotMap.containsValue(null)) {
      int key = attendant.parkThevehicle(parkingLotMap);
      this.parkingLotMap.put(key, vehicle);
      LocalDateTime localDateTime = LocalDateTime.now();
      setParkedTime(localDateTime);
    }

    if(this.parkingLotMap.size()==MAX_PARKING_CAPACITY && !parkingLotMap.containsValue(null)){
      String message = "Parking Lot is Full";
      for(ParkingLotObserver observer:observers){
        observer.update(message);
      }
    }
  }

  /**
   *
   * @param time
   */
  public void setParkedTime(LocalDateTime time) {
    this.time = time;
  }

  /**
   *
   * @return Local time
   */
  public LocalDateTime getParkedTime() {
    return this.time;
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
    int nullCount = 0;
    for(Map.Entry map : parkingLotMap.entrySet()){
      if(map.getValue()==null)
        nullCount++;
    }
    if(nullCount==MAX_PARKING_CAPACITY) throw new ParkingLotException("parking lot is empty");
    if(this.parkingLotMap.containsValue(vehicle)) {
      for(Map.Entry map : parkingLotMap.entrySet()){
        if(map.getValue()==vehicle){
          key= (Integer) map.getKey();
        }
      }
      this.parkingLotMap.put(key,null);
      if(this.parkingLotMap.containsValue(null)) {
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

  /**
   *
   * @param vehicle
   * @return vehicle location in the parking lot
   */
  public int getVehicleLoacation(Vehicle vehicle) {
    return getVehicleLotNumber(vehicle);
  }
}
