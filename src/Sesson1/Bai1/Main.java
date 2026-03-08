package Sesson1.Bai1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Vui lòng nhập năm sinh của bạn: ");
            String input = sc.nextLine();

            int yearOfBirth = Integer.parseInt(input);

            int currentYear = java.time.Year.now().getValue();
            int age = currentYear - yearOfBirth;

            if  (age < 0) {
                System.out.println("Năm sinh không hợp lệ. Vui lòng nhập một năm sinh hợp lệ.");
            } else {
                System.out.println("Đăng ký thành công tuổi của bạn là: " + age);
            }

        } catch (NumberFormatException e) {
            // Bắt lỗi nếu người dùng nhập chữ thay vì số
            System.out.println("Lỗi: Bạn đang nhập ký tự không phải là số. Vui lòng nhập một năm sinh hợp lệ.");
        } finally {
            sc.close();
            System.out.println("Đang dọn dẹp tài nguyên...");
        }

        System.out.println("Chương trình kết thúc.");

    }
}
