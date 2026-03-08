package Sesson1.Bai4;

import java.io.IOException;

public class FileService {

    // --- METHOD C: Tầng thấp nhất (Gốc rễ phát sinh lỗi) ---
    public void saveToFile() throws IOException {
        System.out.println("[Method C] Đang kết nối tới hệ thống lưu trữ...");

        // Giả lập lỗi: Ổ đĩa bị ngắt kết nối hoặc không có quyền ghi
        boolean isError = true;
        if (isError) {
            throw new IOException("Lỗi kết nối: Không tìm thấy ổ đĩa lưu trữ (Disk Not Found).");
        }

        System.out.println("[Method C] Ghi file thành công!");
    }

    // --- METHOD B: Tầng trung gian (Xử lý nghiệp vụ) ---
    public void processUserData() throws IOException {
        System.out.println("[Method B] Đang chuẩn bị gói dữ liệu người dùng...");
        // Gọi Method C
        saveToFile();
        System.out.println("[Method B] Hoàn tất xử lý gói dữ liệu.");
    }
}