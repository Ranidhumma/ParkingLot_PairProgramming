package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author -> Karthik M C,RaniDhumma
 * @since -> 25/04/2022
 */
public class ParkingLotTest {
  private ParkingLotSystem parkingLotSystem;
  @Before
  public void setUp() throws Exception {
    parkingLotSystem = new ParkingLotSystem();
  }

  /*
      UC1
      Test to check the vehicle is parked
        after calling park method
       */
  @Test
  public void givenAVehicle_WhenParked_ShouldReturnTrue() {
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
    parkingLotSystem.park(vehicle);
    boolean isParked = parkingLotSystem.park(vehicle1);
    Assert.assertFalse(isParked);
  }
  
  /** UC2-Test to check the vehicle is unparked after calling unpark method */
  @Test
  public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
      Vehicle vehicle = new Vehicle();
      parkingLotSystem.park(vehicle);
      boolean isUnParked = parkingLotSystem.unPark(vehicle);
      Assert.assertTrue(isUnParked);
  }

  /** UC2 test 2 to check the vehicle is not parked but asking to unpark should return false */
  @Test
  public void givenAVehicle_WhenTryToUnPark_WhenNotParked_ShouldreturnFalse() {
    Vehicle vehicle = new Vehicle();
    boolean isUnParked = parkingLotSystem.unPark(vehicle);
    Assert.assertFalse(isUnParked);
  }
}
