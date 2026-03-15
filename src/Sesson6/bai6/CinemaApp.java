package Sesson6.bai6;
import Sesson6.bai6.CinemaRoom;
import Sesson6.bai6.DeadlockDetector;
import Sesson6.bai6.TicketCounter;

import java.util.*;
import java.util.concurrent.*;

public class CinemaApp {
    private static List<CinemaRoom> rooms = new ArrayList<>();
    private static List<TicketCounter> counters = new ArrayList<>();
    private static ExecutorService executor;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n======= MÔ PHỎNG RẠP CHIẾU PHIM =======");
            System.out.println("1. Bắt đầu mô phỏng   2. Tạm dừng     3. Tiếp tục");
            System.out.println("4. Thêm vé vào phòng  5. Xem thống kê 6. Quét Deadlock");
            System.out.println("7. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    setupAndStart(sc);
                    break;
                case 2:
                    counters.forEach(c -> c.setPaused(true));
                    System.out.println("Đã tạm dừng tất cả các quầy.");
                    break;
                case 3:
                    counters.forEach(c -> c.setPaused(false));
                    System.out.println("Đã tiếp tục hoạt động.");
                    break;
                case 5:
                    showStats();
                    break;
                case 6:
                    DeadlockDetector.scan();
                    break;
                case 7:
                    if (executor != null) executor.shutdownNow();
                    System.out.println("Đang dừng hệ thống... Tạm biệt!");
                    return;
            }
        }
    }

    private static void setupAndStart(Scanner sc) {
        System.out.print("Nhập số phòng: "); int numRooms = sc.nextInt();
        System.out.print("Số vé mỗi phòng: "); int tpp = sc.nextInt();
        System.out.print("Số quầy bán vé: "); int numCounters = sc.nextInt();

        for (int i = 1; i <= numRooms; i++) rooms.add(new CinemaRoom("Phòng " + i, tpp));

        executor = Executors.newFixedThreadPool(numCounters);
        for (int i = 1; i <= numCounters; i++) {
            TicketCounter counter = new TicketCounter(i, rooms);
            counters.add(counter);
            executor.execute(counter);
        }
        System.out.println("Hệ thống đã khởi chạy với " + numCounters + " quầy.");
    }

    private static void showStats() {
        System.out.println("\n=== THỐNG KÊ HIỆN TẠI ===");
        long totalRevenue = 0;
        for (CinemaRoom r : rooms) {
            System.out.println("  " + r.getName() + ": Đã bán " + r.getSold() + "/" + r.getTotal());
            totalRevenue += r.getRevenue();
        }
        System.out.println("  Tổng doanh thu: " + String.format("%, d", totalRevenue) + " VNĐ");
    }
}