package Sesson4.Bai4;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {

    private final PasswordValidator validator = new PasswordValidator();

    @Test
    void testEvaluatePasswordStrength_AllCases() {
        assertAll("Kiểm tra các kịch bản độ mạnh mật khẩu",
                () -> assertEquals("Mạnh", validator.evaluatePasswordStrength("Abc123!@"), "TC01 thất bại"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("abc123!@"), "TC02 thất bại"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("ABC123!@"), "TC03 thất bại"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("Abcdef!@"), "TC04 thất bại"),
                () -> assertEquals("Trung bình", validator.evaluatePasswordStrength("Abc12345"), "TC05 thất bại"),
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("Ab1!"), "TC06 thất bại"),
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("password"), "TC07 thất bại"),
                () -> assertEquals("Yếu", validator.evaluatePasswordStrength("ABC12345"), "TC08 thất bại")
        );
    }
}