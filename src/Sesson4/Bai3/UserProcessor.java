package Sesson4.Bai3;
public class UserProcessor {

    public String processEmail(String email) {
        if (email == null || !email.contains("@") || email.endsWith("@")) {
            throw new IllegalArgumentException("Định dạng email không hợp lệ");
        }

        String[] parts = email.split("@");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Email thiếu tên miền");
        }

        return email.toLowerCase();
    }
}
