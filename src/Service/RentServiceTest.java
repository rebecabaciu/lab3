package Service;

import Domain.Car;
import Domain.Rent;
import Exception.RentException;
import Repository.Repository;
import Repository.RepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RentServiceTest {

    private RentService rentService;
    private RepositoryInterface<Rent> rentRepository;

    @BeforeEach
    void setUp() {
        rentRepository = new Repository<Rent>();
        rentService = new RentService(rentRepository);
    }

    @Test
    void testAddRent() throws RentException, IOException {
        Car car = new Car(1, "Toyota", "Camry");
        rentService.addRent(1, car, "2023-01-01", "2023-01-10");

        List<Rent> rents = rentService.getAllRents();
        assertEquals(1, rents.size());
        assertEquals("Camry", rents.get(0).getCar().getModel());
    }

    @Test
    void testAddRentWithNegativeId() {
        Car car = new Car(1, "Toyota", "Camry");
        assertThrows(RentException.class, () -> rentService.addRent(-1, car, "2023-01-01", "2023-01-10"));
    }

    @Test
    void testAddRentWithDuplicateId() throws RentException, IOException {
        Car car = new Car(1, "Toyota", "Camry");
        rentService.addRent(1, car, "2023-01-01", "2023-01-10");

        assertThrows(RentException.class, () -> rentService.addRent(1, car, "2023-01-05", "2023-01-15"));
    }

    @Test
    void testGetRentById() throws RentException, IOException {
        Car car = new Car(1, "Toyota", "Camry");
        rentService.addRent(1, car, "2023-01-01", "2023-01-10");

        Rent rent = rentService.getRentById(1);
        assertNotNull(rent);
        assertEquals("2023-01-01", rent.getStartDate());
    }

    @Test
    void testGetRentByIdNotFound() {
        assertThrows(RentException.class, () -> rentService.getRentById(1));
    }

    @Test
    void testUpdateRent() throws RentException, IOException {
        Car car = new Car(1, "Toyota", "Camry");
        rentService.addRent(1, car, "2023-01-01", "2023-01-10");

        rentService.updateRent(1, "2023-01-05", "2023-01-15");

        Rent updatedRent = rentService.getRentById(1);
        assertEquals("2023-01-05", updatedRent.getStartDate());
        assertEquals("2023-01-15", updatedRent.getEndDate());
    }

    @Test
    void testUpdateRentNotFound() {
        assertThrows(RentException.class, () -> rentService.updateRent(1, "2023-01-05", "2023-01-15"));
    }

    @Test
    void testDeleteRent() throws RentException, IOException {
        Car car = new Car(1, "Toyota", "Camry");
        rentService.addRent(1, car, "2023-01-01", "2023-01-10");

        rentService.deleteRent(1);

        List<Rent> rents = rentService.getAllRents();
        assertEquals(0, rents.size());
    }

    @Test
    void testDeleteRentNotFound() {
        assertThrows(RentException.class, () -> rentService.deleteRent(1));
    }
}
