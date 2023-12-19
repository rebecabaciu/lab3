package Domain;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CarFactory implements IEntityFactory<Car> {
    @Override
    public Car createEntity(String line) {
        int id = Integer.parseInt(line.split(",")[0]);
        String brand = line.split(",")[1];
        String model = line.split(",")[2];

        return new Car(id, brand, model);
    }
    @Override
    public String toStringEntity(Car car) {
        return car.getId() + "," + car.getBrand() + "," + car.getModel();
    }

}
