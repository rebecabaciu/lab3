package Domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import static org.junit.Assert.*;

public class RentTest {

    @Test
    public void testToString() {
        Car car = new Car(1, "Toyota", "Camry");
        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");
        String expectedString = "\n" +
                "******************\n" +
                "Id: 1\n" +
                "Car: 1 Toyota Camry\n" +
                "StartDate: 2023-06-10\n" +
                "EndDate: 2023-06-15\n" +
                "******************\n";
        assertEquals(expectedString, rent.toString());
    }

    @Test
    public void testGetCar() {
        Car car = new Car(1, "Toyota", "Camry");
        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");
        assertEquals(car, rent.getCar());
    }

    @Test
    public void testGetStartDate() {
        Car car = new Car(1, "Toyota", "Camry");
        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");
        assertEquals("2023-06-10", rent.getStartDate());
    }

    @Test
    public void testGetEndDate() {
        Car car = new Car(1, "Toyota", "Camry");
        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");
        assertEquals("2023-06-15", rent.getEndDate());
    }

    @Test
    public void testSetCar() {
        Car car1 = new Car(1, "Toyota", "Camry");
        Car car2 = new Car(2, "Honda", "Accord");
        Rent rent = new Rent(1, car1, "2023-06-10", "2023-06-15");
        rent.setCar(car2);
        assertEquals(car2, rent.getCar());
    }

    @Test
    public void testSetStartDate() {
        Car car = new Car(1, "Toyota", "Camry");
        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");
        rent.setStartDate("2023-06-20");
        assertEquals("2023-06-20", rent.getStartDate());
    }

    @Test
    public void testSetEndDate() {
        Car car = new Car(1, "Toyota", "Camry");
        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");
        rent.setEndDate("2023-06-25");
        assertEquals("2023-06-25", rent.getEndDate());
    }

    @Test
    public void testCreateEntity() {
        RentFactory rentFactory = new RentFactory();

        String inputLine = "1,2,Toyota,Camry,2023-06-10,2023-06-15";
        Rent rent = rentFactory.createEntity(inputLine);

        assertEquals(1, rent.getId());
        assertEquals(2, rent.getCar().getId());
        assertEquals("Toyota", rent.getCar().getBrand());
        assertEquals("Camry", rent.getCar().getModel());
        assertEquals("2023-06-10", rent.getStartDate());
        assertEquals("2023-06-15", rent.getEndDate());
    }

    @Test
    public void testToStringEntity() {
        RentFactory rentFactory = new RentFactory();

        Car car = new Car(2, "Toyota", "Camry");
        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");

        String expectedOutput = "1,2,Toyota,Camry,2023-06-10,2023-06-15";
        String actualOutput = rentFactory.toStringEntity(rent);

        assertEquals(expectedOutput, actualOutput);
    }
}

