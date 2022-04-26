package com.bridgelabz.parkinglot;
/**
 * @author -> Karthik M C,RaniDhumma
 * @since -> 25/04/2022
 */
public class ParkingLotSystem {
  private Vehicle vehicle;

  /**
   *
   * @param vehicle
   * @return if vehicle is parked return true else return false
   */
  public boolean park(Vehicle vehicle) {
    if (this.vehicle != null) return false;
    this.vehicle = vehicle;
    return true;
  }

  /**
   *
   * @param vehicle
   * @return if unparked return true else return false
   */
  public boolean unPark(Vehicle vehicle) {
    if(this.vehicle==null) return false;
    if(this.vehicle.equals(vehicle)) return true;
    return false;
  }
}
