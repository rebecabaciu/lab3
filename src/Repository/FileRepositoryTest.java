package Repository;
import Domain.Car;
import Domain.CarFactory;
import Domain.IEntityFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileRepositoryTest {

    private FileRepository<Car> carRepository;
    private IEntityFactory<Car> entityFactory;

    @BeforeEach
    void setUp() throws IOException {
        String tempFileName = "cars_test.txt";
        entityFactory = new CarFactory();
        carRepository = new FileRepository<>(tempFileName, entityFactory);
    }

    @Test
    void testAdd() throws IOException {
        carRepository.clear();
        carRepository.add(new Car(3, "Ford", "Mustang"));

        assertEquals(1, carRepository.size());
    }

    @Test
    void testDelete() throws IOException {
        carRepository.clear();
        carRepository.add(new Car(1, "Ford", "Mustang"));
        Car carToDelete = new Car(1, "Toyota", "Camry");
        carRepository.delete(carToDelete);

        assertEquals(1, carRepository.size());
    }

    @Test
    void testUpdate() throws IOException {
        carRepository.clear();
        carRepository.add(new Car(1, "Ford", "Mustang"));
        Car updatedCar = new Car(1, "Toyota", "Corolla");
        carRepository.update(updatedCar);

        assertEquals("Corolla", carRepository.getById(1).getModel());
    }

    @Test
    void testReadFile() {
        carRepository.clear();
        assertEquals(0, carRepository.size());
    }

    @Test
    void testInvalidFile() {
        assertThrows(FileNotFoundException.class, () -> new FileRepository<>("nonexistentfile.txt", entityFactory));
    }
}

