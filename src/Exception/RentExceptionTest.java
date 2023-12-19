package Exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RentExceptionTest {

    @Test
    void testRentExceptionMessage() {
        String errorMessage = "Invalid rent exception message";
        RentException rentException = new RentException(errorMessage);

        assertEquals(errorMessage, rentException.getMessage());
    }
}
