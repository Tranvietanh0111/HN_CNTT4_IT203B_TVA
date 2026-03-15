package Sesson6.bai6;
import java.util.concurrent.atomic.AtomicInteger;

public class CinemaRoom {
    private String name;
    private AtomicInteger availableTickets;
    private AtomicInteger soldTickets = new AtomicInteger(0);
    private final int price = 150000; // Giá vé cố định

    public CinemaRoom(String name, int totalTickets) {
        this.name = name;
        this.availableTickets = new AtomicInteger(totalTickets);
    }

    public boolean sellTicket() {
        if (availableTickets.get() > 0) {
            availableTickets.decrementAndGet();
            soldTickets.incrementAndGet();
            return true;
        }
        return false;
    }

    public String getName() { return name; }
    public int getSold() { return soldTickets.get(); }
    public int getTotal() { return availableTickets.get() + soldTickets.get(); }
    public long getRevenue() { return (long) soldTickets.get() * price; }
}