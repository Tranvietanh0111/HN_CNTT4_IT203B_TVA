package Sesson4.Bai4;
public class PasswordValidator {
    public String evaluatePasswordStrength(String password) {
        if (password == null || password.length() < 8) {
            return "Yếu";
        }

        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasLower = password.matches(".*[a-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        if (hasUpper && hasLower && hasDigit && hasSpecial) {
            return "Mạnh";
        }
        int criteriaCount = 0;
        if (hasUpper) criteriaCount++;
        if (hasLower) criteriaCount++;
        if (hasDigit) criteriaCount++;
        if (hasSpecial) criteriaCount++;

        if (criteriaCount >= 3) {
            return "Trung bình";
        }

        return "Yếu";
    }
}