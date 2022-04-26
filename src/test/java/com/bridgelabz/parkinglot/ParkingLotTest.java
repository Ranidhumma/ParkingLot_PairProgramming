package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author -> Karthik M C,RaniDhumma
 * @since -> 25/04/2022
 */
public class ParkingLotTest {
    /*
    Test to check the vehicle is parked
      after calling park method
     */
  @Test
  public void givenAVehicle_WhenParked_ShouldReturnTrue() {
      ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
      boolean isParked = parkingLotSystem.park(new Vehicle());
      Assert.assertTrue(isParked);
  }

  /*
      UC1
     test to check the vehicle is parked if we are trying to park
     another vehicle it should return false
  */
  @Test
  public void givenAVehicleAlreadyParked_WhenTryToParkAnotherVehicle_ShouldReturnFalse() {
    Vehicle vehicle = new Vehicle();
    Vehicle vehicle1 = new Vehicle();
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
    parkingLotSystem.park(vehicle);
    boolean isParked = parkingLotSystem.park(vehicle1);
    Assert.assertFalse(isParked);
  }

}
