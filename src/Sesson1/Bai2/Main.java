package Sesson1.Bai2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Nhập tổng số người dùng: ");
            int totalUsers = sc.nextInt();

            System.out.println("Nhập số lượng nhóm muốn chia: ");
            int groups = sc.nextInt();

            int usersPerGroup = totalUsers / groups;

            System.out.println("Kết quả mỗi nhóm sẽ có " + usersPerGroup + " người dùng.");

        } catch (ArithmeticException e) {
            System.out.println("Cảnh báo: Không thể chia cho 0! Vui lòng nhập lại.");
        } finally {
            sc.close();
            System.out.println("Giải phóng tài nguyên ....");
        }

        System.out.println("Chương trình kết thúc.");
    }
}
