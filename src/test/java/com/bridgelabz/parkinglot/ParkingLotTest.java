package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * @author -> Karthik M C,RaniDhumma
 * @since -> 25/04/2022
 */
public class ParkingLotTest {
    private ParkingLotSystem parkingLotSystem;
    private Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Vehicle(1,"car1");

    }

    /*
    UC1
    Test to check the vehicle is parked
      after calling park method
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle, DriverType.NORMAL, CarType.LARGE);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
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
            Vehicle vehicle1 = new Vehicle(2,"car2");
            parkingLotSystem.park(vehicle, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(vehicle1, DriverType.NORMAL, CarType.LARGE);
        } catch (ParkingLotException e) {
            Assert.assertEquals("parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * UC2-Test to check the vehicle is unparked after calling unpark method
     */
    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.park(vehicle, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.unPark(vehicle);
            boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
            Assert.assertTrue(isUnParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC2 test 2 to check the vehicle is not parked but asking to unpark should return false
     */
    @Test
    public void givenAVehicle_WhenTryToUnPark_WhenNotParked_ShouldThrowException() {
        try {
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
            Vehicle vehicle1 = new Vehicle(2,"car2");
            parkingLotSystem.park(vehicle, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.unPark(vehicle1);
        } catch (ParkingLotException e) {
            Assert.assertEquals("Ask for correct vehicle", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Test to check updating parkingLot Full message to owner
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToOwner() {
        try {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerObservers(owner);
            parkingLotSystem.park(vehicle, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(2,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(3,"car3"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(4,"car4"), DriverType.NORMAL, CarType.LARGE);
            String status = owner.getStatus();
            Assert.assertEquals("Parking Lot is Full", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test to check updating parkingLot Full message to Security Personnel
     */
    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldGiveMessageToSecurityPersonnel() {
        try {
            SecurityPersonnel securityPersonnel = new SecurityPersonnel();
            parkingLotSystem.registerObservers(securityPersonnel);
            parkingLotSystem.park(vehicle, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(2,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(3,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(4,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(4,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(4,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(4,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(4,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(4,"car2"), DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(new Vehicle(4,"car2"), DriverType.NORMAL, CarType.LARGE);
            String status = securityPersonnel.getStatus();
            Assert.assertEquals("Parking Lot is Full", status);
        } catch (ParkingLotException e) {

            e.printStackTrace();
        }
    }

    /**
     * Test to check updating parkingLot empty message to Security Personnel
     */
    @Test
    public void givenAVehicle_WhenParkingLotHasSpaceAgain_ShouldGiveMessageToOwner() {
        try {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerObservers(owner);
            parkingLotSystem.park(vehicle, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.unPark(vehicle);
            String status = owner.getStatus();
            Assert.assertEquals("Parkinglot has space", status);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }


    /**
     * test to check owner has to generate the key for attendant to park the car
     */
    @Test
    public void givenAttendant_WhenOwnerGivesTheSlotToParkTheVehicle_ShouldPark() {
        Attendant attendant = new Attendant();
        try {
            ParkingLotOwner owner = new ParkingLotOwner();
            parkingLotSystem.registerObservers(owner);
            Vehicle alto = new Vehicle(1,"car1");
            Vehicle etios = new Vehicle(2,"car2");
            Vehicle brezza = new Vehicle(3,"car3");
            Vehicle ertiga = new Vehicle(4,"car4");
            parkingLotSystem.park(alto, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(etios, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(brezza, DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(ertiga, DriverType.NORMAL, CarType.LARGE);
            int vehicleLotNumber = parkingLotSystem.getVehicleLoacation(etios);
            Assert.assertEquals(7,vehicleLotNumber);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC7
     * Test for checking finding the location of given vehicle for driver
     * @throws ParkingLotException
     */
    @Test
    public void givenVehicle_WhenFindVehicle_ShouldReturnKey() throws ParkingLotException {
        Vehicle v1 = new Vehicle(1, "alto");
        Vehicle v2 = new Vehicle(2, "brezza");
        Vehicle v3 = new Vehicle(3, "creta");
        parkingLotSystem.park(v1, DriverType.NORMAL, CarType.LARGE);
        parkingLotSystem.park(v2, DriverType.NORMAL, CarType.LARGE);
        parkingLotSystem.unPark(v1);
        parkingLotSystem.park(v3, DriverType.NORMAL, CarType.LARGE);

        int key = parkingLotSystem.getVehicleLoacation(v3);
        Assert.assertEquals(6, key);
    }

    /**
     * UC8 as a parking lot owner I want to know when the car was parked
     * on my lot
     */
    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTimeOfParking() {
        try {
            Vehicle alto = new Vehicle(1,"car1");
            parkingLotSystem.park(alto, DriverType.NORMAL, CarType.LARGE);
            LocalDateTime localDateTime = LocalDateTime.now();
            Assert.assertEquals(localDateTime,parkingLotSystem.getParkedTime());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    /**
     * UC10 test case to allot parking lot according driver type
     * 1)for NORMAL Driver
     * 2)for HANDICAP Driver
     */
    @Test
    public void givenAVehicle_WhenDriverTypeMentioned_ShouldParkAccordingly() {
        Vehicle alto = new Vehicle(1,"car1");
        Vehicle brezza = new Vehicle(2, "brezza");
        Vehicle creta = new Vehicle(3, "creta");
        try {
            parkingLotSystem.park(alto,DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(brezza,DriverType.NORMAL, CarType.LARGE);
            parkingLotSystem.park(creta,DriverType.HANDICAP, CarType.LARGE);
            int altoLot = parkingLotSystem.getVehicleLoacation(alto);
            int brezzaLot = parkingLotSystem.getVehicleLoacation(brezza);
            int cretaLot = parkingLotSystem.getVehicleLoacation(creta);
            Assert.assertEquals(6,altoLot);
            Assert.assertEquals(7,brezzaLot);
            Assert.assertEquals(1,cretaLot);
        } catch (ParkingLotException e) {
            Assert.assertTrue(parkingLotSystem.getParkingMapSize()<= parkingLotSystem.MAX_PARKING_CAPACITY);
            e.printStackTrace();
        }
    }

    /**
     * UC11 test case to allot parking lot according cartype
     * 1)Large
     * 2)Small
     */
    @Test
    public void givenTwoParkingMaps_WhenLargeVehicleComes_ShouldParkAtLotHavingLargeSpace() {
        Vehicle alto = new Vehicle(1,"alto");
        Vehicle brezza = new Vehicle(2, "brezza");
        Vehicle creta = new Vehicle(3, "creta");
        Vehicle ertiga = new Vehicle(4, "ertiga");


        try {
            parkingLotSystem.park(alto,DriverType.NORMAL,CarType.SMALL);
            parkingLotSystem.park(creta,DriverType.NORMAL,CarType.SMALL);
            parkingLotSystem.park(brezza,DriverType.NORMAL,CarType.LARGE);
            parkingLotSystem.park(ertiga,DriverType.HANDICAP,CarType.LARGE);
            int ertigaLot = parkingLotSystem.getVehicleLoacation(ertiga);
            int brezzaLot = parkingLotSystem.getVehicleLoacation(brezza);
            int cretaLot = parkingLotSystem.getVehicleLoacation(creta);
            Assert.assertEquals(1,ertigaLot);
            Assert.assertEquals(6,brezzaLot);
            Assert.assertEquals(7,cretaLot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
