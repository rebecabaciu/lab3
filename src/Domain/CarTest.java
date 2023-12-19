package Domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void testToString() {
        Car car = new Car(1, "Toyota", "Camry");
        String expectedString = "\n" +
                "******************\n" +
                "Id: 1\n" +
                "Brand:  Toyota\n" +
                "Model: Camry\n" +
                "******************\n";
        assertEquals(expectedString, car.toString());
    }

    @Test
    public void testToSimpleString() {
        Car car = new Car(1, "Toyota", "Camry");
        String expectedString = "Car: 1 Toyota Camry";
        assertEquals(expectedString, car.toSimpleString());
    }

    @Test
    public void testToSimpleString2() {
        Car car = new Car(1, "Toyota", "Camry");
        String expectedString = "1,Toyota,Camry";
        assertEquals(expectedString, car.toSimpleString2());
    }

    @Test
    public void testGetBrand() {
        Car car = new Car(1, "Toyota", "Camry");
        assertEquals("Toyota", car.getBrand());
    }

    @Test
    public void testGetModel() {
        Car car = new Car(1, "Toyota", "Camry");
        assertEquals("Camry", car.getModel());
    }

    @Test
    public void testSetBrand() {
        Car car = new Car(1, "Toyota", "Camry");
        car.setBrand("Honda");
        assertEquals("Honda", car.getBrand());
    }

    @Test
    public void testSetModel() {
        Car car = new Car(1, "Toyota", "Camry");
        car.setModel("Accord");
        assertEquals("Accord", car.getModel());
    }

    @Test
    public void testGetId() {
        Car car = new Car(1, "Toyota", "Camry");
        assertEquals(1, car.getId());
    }

    @Test
    public void testCreateEntity() {
        CarFactory carFactory = new CarFactory();

        String inputLine = "1,Toyota,Camry";
        Car car = carFactory.createEntity(inputLine);

        assertEquals(1, car.getId());
        assertEquals("Toyota", car.getBrand());
        assertEquals("Camry", car.getModel());
    }

    @Test
    public void testToStringEntity() {
        CarFactory carFactory = new CarFactory();

        Car car = new Car(1, "Toyota", "Camry");
        String expectedOutput = "1,Toyota,Camry";
        String actualOutput = carFactory.toStringEntity(car);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEquals() {
        // Arrange
        Car car1 = new Car(1, "Toyota", "Camry");
        Car car2 = new Car(1, "Toyota", "Camry");
        Car car3 = new Car(0, "SomeBrand", "SomeModel"); // Updated to have id 0

        // Act & Assert
        assertTrue(car1.equals(car2)); // Same id, brand, and model
        assertFalse(car1.equals(car3)); // Different id
    }

    @Test
    public void testHashCode() {
        // Arrange
        Car car1 = new Car(1, "Toyota", "Camry");
        Car car2 = new Car(1, "Toyota", "Camry");
        Car car3 = new Car(0, "SomeBrand", "SomeModel"); // Updated to have id 0

        // Act & Assert
        assertEquals(car1.hashCode(), car2.hashCode()); // Same id, brand, and model
        assertNotEquals(car1.hashCode(), car3.hashCode()); // Different id
    }
    @Test
    public void testNoArgConstructor() {
        // Arrange
        Car car = new Car();

        // Act & Assert
        assertEquals(0, car.getId()); // Ensure id is initialized to 0
        assertEquals("", car.getBrand()); // Ensure brand is initialized to an empty string
        assertEquals("", car.getModel()); // Ensure model is initialized to an empty string
    }

}
