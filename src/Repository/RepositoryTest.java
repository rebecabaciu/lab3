package Repository;
import Domain.Car;
import Domain.Rent;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

public class RepositoryTest {

    private Repository<Car> carRepository;
    private Repository<Rent> rentRepository;

    @Before
    public void setUp() {
        carRepository = new Repository<Car>();
        rentRepository = new Repository<Rent>();
    }

    @Test
    public void testAddAndGetAll() throws IOException {
        Car car = new Car(1, "Toyota", "Camry");
        carRepository.add(car);
        assertEquals(1, carRepository.size());
        assertEquals(car, carRepository.getAll().get(0));

        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");
        rentRepository.add(rent);
        assertEquals(1, rentRepository.size());
        assertEquals(rent, rentRepository.getAll().get(0));
    }

    @Test
    public void testGetById() throws IOException {
        Car car = new Car(1, "Toyota", "Camry");
        carRepository.add(car);
        assertEquals(car, carRepository.getById(1));

        Rent rent = new Rent(1, car, "2023-06-10", "2023-06-15");
        rentRepository.add(rent);
        assertEquals(rent, rentRepository.getById(1));
    }

    @Test
    public void testGetByIdNotFound() throws IOException {
        Car car1 = new Car(1, "Toyota", "Camry");
        carRepository.add(car1);

        Car result = carRepository.getById(2);

        assertNull("Expected null, but found a car entity", result);
    }

    @Test
    public void testGetInvalidPosition() throws IOException {
        Car car1 = new Car(1, "Toyota", "Camry");
        carRepository.add(car1);

        assertThrows(NoSuchElementException.class, () -> carRepository.get(1));
    }

    @Test
    public void testUpdate() throws IOException {
        Car car = new Car(1, "Toyota", "Camry");
        carRepository.add(car);

        Car updatedCar = new Car(1, "Honda", "Accord");
        carRepository.update(updatedCar);

        assertEquals("Honda", carRepository.getById(1).getBrand());
        assertEquals("Accord", carRepository.getById(1).getModel());

        Rent rent = new Rent(1, car, "2030-10-01","2030-10-02");
        rentRepository.add(rent);

        Rent updatedRent = new Rent(1,car,"2050-10-10","2050-10-12");
        rentRepository.update(updatedRent);

        assertEquals("2050-10-10", rentRepository.getById(1).getStartDate());
        assertEquals("2050-10-12", rentRepository.getById(1).getEndDate());

    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateNotFound() throws IOException {
        Car car = new Car(1, "Toyota", "Camry");
        carRepository.add(car);

        Car updatedCar = new Car(2, "Honda", "Accord");
        carRepository.update(updatedCar);
    }

    @Test
    public void testDelete() throws IOException {
        Car car = new Car(1, "Toyota", "Camry");
        carRepository.add(car);
        assertEquals(1, carRepository.size());

        carRepository.delete(car);
        assertEquals(0, carRepository.size());
    }
}
