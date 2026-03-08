package Sesson1.Bai3;

public class Main {
    public static void main(String[] args) {
        User user = new User("Phạm Thái Sơn");

        System.out.println("--- Thử nghiệm thiết lập tuổi hợp lệ ---");
        try {
            user.setAge(21);
        } catch (IllegalArgumentException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        System.out.println("\n--- Thử nghiệm thiết lập tuổi âm ---");
        try {
            user.setAge(-5);
        } catch (IllegalArgumentException e) {
            System.err.println("Cảnh báo nghiệp vụ: " + e.getMessage());
        }

        System.out.println("\nChương trình kết thúc an toàn sau khi xử lý ngoại lệ.");
    }
}