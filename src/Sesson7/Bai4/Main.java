package Sesson7.Bai4;


import java.util.ArrayList;
import java.util.List;

class Order {
    private String orderId;

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }
}

interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}

interface NotificationService {
    void send(String message, String recipient);
}

class FileOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào file: " + order.getOrderId());
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>();
    }
}

class EmailService implements NotificationService {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi email: " + message);
    }
}

class DatabaseOrderRepository implements OrderRepository {
    @Override
    public void save(Order order) {
        System.out.println("Lưu đơn hàng vào database: " + order.getOrderId());
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>();
    }
}

class SMSNotification implements NotificationService {
    @Override
    public void send(String message, String recipient) {
        System.out.println("Gửi SMS: " + message);
    }
}

class OrderService {
    private final OrderRepository repository;
    private final NotificationService notification;

    public OrderService(OrderRepository repository, NotificationService notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void createOrder(Order order) {
        repository.save(order);
        notification.send("Đơn hàng " + order.getOrderId() + " đã được tạo", "Khách hàng");
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Dùng FileOrderRepository và EmailService ---");
        OrderRepository fileRepo = new FileOrderRepository();
        NotificationService emailSvc = new EmailService();
        OrderService orderService1 = new OrderService(fileRepo, emailSvc);

        Order order1 = new Order("ORD001");
        orderService1.createOrder(order1);

        System.out.println("\n--- Đổi sang DatabaseOrderRepository và SMSNotification ---");
        OrderRepository dbRepo = new DatabaseOrderRepository();
        NotificationService smsSvc = new SMSNotification();
        OrderService orderService2 = new OrderService(dbRepo, smsSvc);

        Order order2 = new Order("ORD002");
        orderService2.createOrder(order2);
    }
}