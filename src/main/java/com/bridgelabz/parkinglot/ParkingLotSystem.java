package com.bridgelabz.parkinglot;
/**
 * @author -> Karthik M C,RaniDhumma
 * @since -> 25/04/2022
 */
public class ParkingLotSystem {
  private Vehicle vehicle;
  public static Owner owner = new Owner();

  /**
   *
   * @param vehicle
   * @throws ParkingLotException when parking lot is full
   */
  public void park(Vehicle vehicle) throws ParkingLotException {
    if (this.vehicle != null)
      throw new ParkingLotException("parking Lot is Full");
    this.vehicle = vehicle;
    if(this.vehicle!=null){
      String message = "Parking Lot is Full";
      owner.update(message);
    }
  }

  /**
   *
   * @param vehicle
   * @return if vehicle is parked return true else return false
   */
  public boolean isVehicleParked(Vehicle vehicle) {
    if(this.vehicle.equals(vehicle)) return true;
    return false;
  }

  /**
   *
   * @param vehicle
   * @throws ParkingLotException if parking lot is empty and
   * asked for incorrect vehicle
   */
  public void unPark(Vehicle vehicle) throws ParkingLotException {
    if(this.vehicle==null) throw new ParkingLotException("parking lot is empty");
    if(this.vehicle.equals(vehicle)) {
      this.vehicle=null;
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
    if(this.vehicle==null) return true;
    return false;
  }
}
