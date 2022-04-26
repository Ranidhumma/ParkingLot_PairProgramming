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
    try {
      Vehicle vehicle = new Vehicle();
      parkingLotSystem.park(vehicle);
      boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
      Assert.assertTrue(isParked);
    } catch (ParkingLotException e) {
//      e.printStackTrace();
    }
  }

  /*
      UC1
     test to check the vehicle is parked if we are trying to park
     another vehicle it should return false
  */
  @Test
  public void givenAVehicleAlreadyParked_WhenTryToParkAnotherVehicle_ShouldThrowException() {
    try {
      Vehicle vehicle = new Vehicle();
      Vehicle vehicle1 = new Vehicle();
      parkingLotSystem.park(vehicle);
      parkingLotSystem.park(vehicle1);
    } catch (ParkingLotException e) {
      Assert.assertEquals("Parking Lot is Full", e.getMessage());
      e.printStackTrace();
    }
  }

  /** UC2-Test to check the vehicle is unparked after calling unpark method */
  @Test
  public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
    try {
      Vehicle vehicle = new Vehicle();
      parkingLotSystem.park(vehicle);
      parkingLotSystem.unPark(vehicle);
      boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
      Assert.assertTrue(isUnParked);
    } catch (ParkingLotException e) {
      e.printStackTrace();
    }
  }

  /** UC2 test 2 to check the vehicle is not parked but asking to unpark should return false */
  @Test
  public void givenAVehicle_WhenTryToUnPark_WhenNotParked_ShouldThrowException() {
    try {
      Vehicle vehicle = new Vehicle();
      parkingLotSystem.unPark(vehicle);
    } catch (ParkingLotException e) {
      Assert.assertEquals("parking lot is empty", e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * UC2 parked one vehicle and asking to unpark different vehicle should throw exception
   */
  @Test
  public void givenAVehicle_WhenTryToUnParkDifferentVehicle_ShouldThrowException() {
    try {
      Vehicle vehicle = new Vehicle();
      Vehicle vehicle1 = new Vehicle();
      parkingLotSystem.park(vehicle);
      parkingLotSystem.unPark(vehicle1);
    } catch (ParkingLotException e) {
      Assert.assertEquals("Ask for correct vehicle", e.getMessage());
      e.printStackTrace();
    }
  }
}
