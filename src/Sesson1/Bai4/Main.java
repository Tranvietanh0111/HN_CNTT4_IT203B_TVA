package Sesson1.Bai4;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Khởi tạo đối tượng xử lý file
        FileService fileService = new FileService();

        System.out.println("=== KHỞI ĐỘNG HỆ THỐNG KIỂM THỬ LAN TRUYỀN ===");

        try {
            // Gọi tầng trung gian (A -> B)
            fileService.processUserData();

        } catch (IOException e) {
            // Chốt chặn cuối cùng: Xử lý ngoại lệ lan truyền từ C qua B lên đây
            System.err.println("\n[Hàm Main] THÔNG BÁO: Đã chặn đứng ngoại lệ lan truyền!");
            System.err.println("Chi tiết lỗi: " + e.getMessage());

            System.out.println("\n--- Nhật ký truy vết (StackTrace) ---");
            e.printStackTrace();

        } finally {
            // Luôn thực hiện dù có lỗi hay không
            System.out.println("\n[Hàm Main] Đã thực hiện dọn dẹp bộ nhớ (Finally).");
        }

        System.out.println("=== CHƯƠNG TRÌNH KẾT THÚC AN TOÀN ===");
    }
}