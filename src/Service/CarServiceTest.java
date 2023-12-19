package Service;

import Domain.Car;
import Exception.CarException;
import Repository.Repository;
import Repository.RepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarServiceTest {

    private CarService carService;
    private RepositoryInterface<Car> carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new Repository<Car>();
        carService = new CarService(carRepository);
    }

    @Test
    void testAddCar() throws CarException, IOException {
        carService.addCar(1, "Toyota", "Camry");

        List<Car> cars = carService.getAllCars();
        assertEquals(1, cars.size());
        assertEquals("Camry", cars.get(0).getModel());
    }

    @Test
    void testAddCarWithNegativeId() {
        assertThrows(CarException.class, () -> carService.addCar(-1, "Toyota", "Camry"));
    }

    @Test
    void testAddCarWithDuplicateId() throws CarException, IOException {
        carService.addCar(1, "Toyota", "Camry");

        assertThrows(CarException.class, () -> carService.addCar(1, "Honda", "Civic"));
    }

    @Test
    void testGetCarById() throws CarException, IOException {
        carService.addCar(1, "Toyota", "Camry");
        Car car = carService.getCarById(1);

        assertNotNull(car);
        assertEquals("Toyota", car.getBrand());
    }

    @Test
    void testGetCarByIdNotFound() {
        assertThrows(CarException.class, () -> carService.getCarById(1));
    }

    @Test
    void testUpdateCar() throws CarException, IOException {
        carService.addCar(1, "Toyota", "Camry");
        carService.updateCar(1, "Honda", "Accord");

        Car updatedCar = carService.getCarById(1);
        assertEquals("Honda", updatedCar.getBrand());
        assertEquals("Accord", updatedCar.getModel());
    }

    @Test
    void testUpdateCarNotFound() {
        assertThrows(CarException.class, () -> carService.updateCar(1, "Honda", "Accord"));
    }

    @Test
    void testDeleteCar() throws CarException, IOException {
        carService.addCar(1, "Toyota", "Camry");
        carService.deleteCar(1);

        List<Car> cars = carService.getAllCars();
        assertEquals(0, cars.size());
    }

    @Test
    void testDeleteCarNotFound() {
        assertThrows(CarException.class, () -> carService.deleteCar(1));
    }
}
