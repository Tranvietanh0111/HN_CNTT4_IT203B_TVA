package Sesson6.bai6;
import Sesson6.bai6.CinemaRoom;
import java.util.List;
import java.util.Random;

public class TicketCounter implements Runnable {
    private int id;
    private List<CinemaRoom> rooms;
    private volatile boolean paused = false;
    private final Object pauseLock = new Object();

    public TicketCounter(int id, List<CinemaRoom> rooms) {
        this.id = id;
        this.rooms = rooms;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        if (!paused) {
            synchronized (pauseLock) {
                pauseLock.notifyAll(); // Đánh thức thread khi tiếp tục
            }
        }
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (pauseLock) {
                    while (paused) pauseLock.wait(); // Dừng tại đây nếu đang paused
                }

                // Chọn ngẫu nhiên một phòng còn vé
                CinemaRoom room = rooms.get(rand.nextInt(rooms.size()));
                if (room.sellTicket()) {
                    System.out.println("   [Quầy " + id + "] Đã bán 1 vé: " + room.getName());
                }

                Thread.sleep(rand.nextInt(1000) + 500); // Giả lập thời gian giao dịch
            }
        } catch (InterruptedException e) {
            System.out.println("   [Quầy " + id + "] Đang đóng...");
        }
    }
}