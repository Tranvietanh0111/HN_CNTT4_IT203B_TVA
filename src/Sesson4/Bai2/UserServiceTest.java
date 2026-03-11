package Sesson4.Bai2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final UserService userService = new UserService();

    @Test
    void testAgeIsExactly18() {
        boolean result = userService.checkRegistrationAge(18);
        assertEquals(true, result);
    }

    @Test
    void testAgeIsUnder18() {
        boolean result = userService.checkRegistrationAge(17);
        assertEquals(false, result);
    }

    @Test
    void testNegativeAgeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            userService.checkRegistrationAge(-1);
        });
    }
}