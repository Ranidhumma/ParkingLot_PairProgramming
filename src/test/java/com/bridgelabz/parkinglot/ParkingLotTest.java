package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author -> Karthik M C,RaniDhumma
 * @since -> 25/04/2022
 */
public class ParkingLotTest {
    /*
    UC1
    Test to check the vehicle is parked
      after calling park method
     */
  @Test
  public void givenAVehicle_WhenParked_ShouldReturnTrue() {
      ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
      boolean isParked = parkingLotSystem.park(new Vehicle());
      Assert.assertTrue(isParked);
  }

  /** UC2-Test to check the vehicle is unparked after calling unpark method */
  @Test
  public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
      Vehicle vehicle = new Vehicle();
      ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
      parkingLotSystem.park(vehicle);
      boolean isUnParked = parkingLotSystem.unPark(vehicle);
      Assert.assertTrue(isUnParked);
  }
}
