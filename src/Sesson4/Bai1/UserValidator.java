package Sesson4.Bai1;

public class UserValidator {

    public boolean isValidUsername(String username) {
        if (username == null) {
            return false;
        }

        int length = username.length();
        boolean hasNoSpace = !username.contains(" ");

        return length >= 6 && length <= 20 && hasNoSpace;
    }
}