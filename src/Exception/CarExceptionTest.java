package Exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarExceptionTest {

    @Test
    void testCarExceptionMessage() {
        String errorMessage = "Invalid car exception message";
        CarException carException = new CarException(errorMessage);

        assertEquals(errorMessage, carException.getMessage());
    }
}
