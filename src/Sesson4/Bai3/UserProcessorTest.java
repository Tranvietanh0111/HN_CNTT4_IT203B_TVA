package Sesson4.Bai3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserProcessorTest {

    private UserProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }

    @Test
    void testProcessEmail_ValidEmail_ReturnsEmail() {
        String input = "user@gmail.com";
        String result = processor.processEmail(input);
        assertEquals("user@gmail.com", result);
    }

    @Test
    void testProcessEmail_MissingAtSign_ThrowsException() {
        String input = "usergmail.com";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(input);
        });
    }

    @Test
    void testProcessEmail_MissingDomain_ThrowsException() {
        String input = "user@";
        assertThrows(IllegalArgumentException.class, () -> {
            processor.processEmail(input);
        });
    }

    @Test
    void testProcessEmail_NormalizationToLowerCase() {
        String input = "Example@Gmail.com";
        String result = processor.processEmail(input);
        assertEquals("example@gmail.com", result);
    }
}