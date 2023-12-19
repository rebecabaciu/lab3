import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SettingsTest {

    @Test
    void testGetInstance() {
        // Assuming your settings.properties file contains appropriate values
        Settings settings = Settings.getInstance();

        // Test the values retrieved from the settings.properties file
        assertEquals("binary", settings.getRepoType());
        assertEquals("cars.bin", settings.getRepoFileCar());
        assertEquals("rents.bin", settings.getRepoFileRent());
    }
}
