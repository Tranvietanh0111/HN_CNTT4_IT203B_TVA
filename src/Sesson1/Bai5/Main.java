package Sesson1.Bai5;
public class Main {
    public static void main(String[] args) {
        User user = new User("Phạm Thái Sơn");

        System.out.println("--- Thử nghiệm 1: Nhập tuổi hợp lệ ---");
        try {
            user.setAge(21);
        } catch (InvalidAgeException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\n--- Thử nghiệm 2: Nhập tuổi âm ---");
        try {
            user.setAge(-10);
        } catch (InvalidAgeException e) {
            // In ra thông báo lỗi chuyên biệt
            System.err.println("Phát hiện lỗi: " + e.getMessage());
        }

        System.out.println("\n--- Thử nghiệm 3: Nhập tuổi quá lớn ---");
        try {
            user.setAge(200);
        } catch (InvalidAgeException e) {
            System.err.println("Phát hiện lỗi: " + e.getMessage());
        }

        System.out.println("\nChương trình kết thúc an toàn.");
    }
}