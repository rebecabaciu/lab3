package Repository;

import Domain.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryFileRepositoryTest {

    private BinaryFileRepository<Car> carRepository;

    @BeforeEach
    void setUp() throws IOException, ClassNotFoundException {
        String tempFileName = "test_cars.bin";
        carRepository = new BinaryFileRepository<Car>(tempFileName);

        carRepository.clear();
    }

    @Test
    void testAdd() throws IOException {
        carRepository.add(new Car(1, "Toyota", "Camry"));
        carRepository.add(new Car(2, "Honda", "Accord"));

        List<Car> cars = carRepository.getAll();
        assertEquals(2, cars.size());
    }

    @Test
    void testDelete() throws IOException {
        carRepository.add(new Car(1, "Toyota", "Camry"));
        carRepository.add(new Car(2, "Honda", "Accord"));

        Car carToDelete = new Car(1, "Toyota", "Camry");
        carRepository.delete(carToDelete);

        ArrayList<Car> cars = carRepository.getAll();
        assertEquals(1, cars.size());
    }

    @Test
    void testUpdate() throws IOException {
        carRepository.add(new Car(1, "Toyota", "Camry"));
        carRepository.add(new Car(2, "Honda", "Accord"));

        Car updatedCar = new Car(1, "Toyota", "Corolla");
        carRepository.update(updatedCar);

        assertEquals("Corolla", carRepository.getById(1).getModel());
    }

    @Test
    void testReadFileEmpty() throws IOException {
        // Assuming the file is empty, check if getAll returns an empty list
        List<Car> cars = carRepository.getAll();
        assertTrue(cars.isEmpty());
    }

    @Test
    void testReadFileNonEmpty() throws IOException, ClassNotFoundException {
        carRepository.add(new Car(1, "Toyota", "Camry"));
        carRepository.add(new Car(2, "Honda", "Accord"));

        // Create a new repository to simulate reading from the file
        BinaryFileRepository<Car> newRepository = new BinaryFileRepository<Car>("test_cars.bin");

        // Check if the data was correctly read from the file
        List<Car> cars = newRepository.getAll();
        assertEquals(2, cars.size());
    }
}
