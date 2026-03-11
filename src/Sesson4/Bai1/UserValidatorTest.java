package Sesson4.Bai1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    private final UserValidator validator = new UserValidator();

    @Test
    void TC01_ValidUsername() {
        String username = "user123";
        boolean result = validator.isValidUsername(username);
        assertTrue(result);
    }

    @Test
    void TC02_UsernameTooShort() {
        String username = "abc";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }

    @Test
    void TC03_UsernameContainsSpace() {
        String username = "user name";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }
}