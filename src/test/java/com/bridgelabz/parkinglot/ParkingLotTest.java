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

}
