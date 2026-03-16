package Sesson7.Bai5;

import java.util.*;
class Product {
    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}

class Customer {
    private String name;
    private String email;

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getSubTotal() { return product.getPrice() * quantity; }
}

class Order {
    private String id;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private double totalAmount;
    private double finalAmount;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }
    public void addItem(OrderItem item) { items.add(item); }
    public String getId() { return id; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() {
        return items.stream().mapToDouble(OrderItem::getSubTotal).sum();
    }
    public double getFinalAmount() { return finalAmount; }
    public void setFinalAmount(double finalAmount) { this.finalAmount = finalAmount; }
}

interface DiscountStrategy {
    double applyDiscount(double amount);
    String getName();
}

interface PaymentMethod {
    void process(double amount);
    String getName();
}

interface OrderRepository {
    void save(Order order);
    List<Order> findAll();
}
interface NotificationService {
    void send(String message, String recipient);
}
class PercentageDiscount implements DiscountStrategy {
    private double rate;
    public PercentageDiscount(double rate) { this.rate = rate; }
    @Override public double applyDiscount(double amount) { return amount * (rate / 100); }
    @Override public String getName() { return rate + "%"; }
}

class CreditCardPayment implements PaymentMethod {
    @Override public void process(double amount) { System.out.println("Thanh toán thẻ tín dụng: " + (long)amount); }
    @Override public String getName() { return "Thẻ tín dụng"; }
}

class DatabaseOrderRepository implements OrderRepository {
    private List<Order> db = new ArrayList<>();
    @Override public void save(Order order) { db.add(order); System.out.println("Đã lưu đơn hàng " + order.getId()); }
    @Override public List<Order> findAll() { return db; }
}

class EmailNotification implements NotificationService {
    @Override public void send(String msg, String to) { System.out.println("Đã gửi email đến " + to + ": " + msg); }
}
class InvoiceGenerator {
    public void generate(Order order, double discount) {
        System.out.println("\n=== HÓA ĐƠN ===");
        System.out.println("Khách: " + order.getCustomer().getName());
        for (OrderItem item : order.getItems()) {
            System.out.println(item.getProduct().getName() + " - SL: " + item.getQuantity() + " - Đơn giá: " + (long)item.getProduct().getPrice());
        }
        System.out.println("Tổng tiền: " + (long)order.getTotalAmount());
        System.out.println("Giảm giá: " + (long)discount);
        System.out.println("Cần thanh toán: " + (long)order.getFinalAmount());
    }
}

class OrderService {
    private OrderRepository repo;
    private NotificationService notify;
    private InvoiceGenerator invoice;

    public OrderService(OrderRepository repo, NotificationService notify, InvoiceGenerator invoice) {
        this.repo = repo;
        this.notify = notify;
        this.invoice = invoice;
    }

    public void processOrder(Order order, DiscountStrategy discount, PaymentMethod payment) {
        double total = order.getTotalAmount();
        double discountVal = discount.applyDiscount(total);
        order.setFinalAmount(total - discountVal);

        payment.process(order.getFinalAmount());
        invoice.generate(order, discountVal);
        repo.save(order);
        notify.send("Đơn hàng " + order.getId() + " đã được tạo", order.getCustomer().getEmail());
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        OrderRepository repo = new DatabaseOrderRepository();
        OrderService service = new OrderService(repo, new EmailNotification(), new InvoiceGenerator());

        while (true) {
            System.out.println("\n1. Thêm SP | 2. Thêm Khách | 3. Tạo Đơn | 4. Xem Đơn | 5. Doanh thu | 0. Thoát");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                System.out.print("Mã: "); String id = sc.nextLine();
                System.out.print("Tên: "); String name = sc.nextLine();
                System.out.print("Giá: "); double price = Double.parseDouble(sc.nextLine());
                products.add(new Product(id, name, price));
                System.out.println("Đã thêm sản phẩm " + id);
            } else if (choice == 2) {
                System.out.print("Tên: "); String name = sc.nextLine();
                System.out.print("Email: "); String email = sc.nextLine();
                customers.add(new Customer(name, email));
                System.out.println("Đã thêm khách hàng");
            } else if (choice == 3) {
                if (products.isEmpty() || customers.isEmpty()) continue;
                Order order = new Order("ORD00" + (repo.findAll().size() + 1), customers.get(0));
                order.addItem(new OrderItem(products.get(0), 1));

                service.processOrder(order, new PercentageDiscount(10), new CreditCardPayment());
            } else if (choice == 4) {
                repo.findAll().forEach(o -> System.out.println(o.getId() + " - " + o.getCustomer().getName() + " - " + (long)o.getFinalAmount()));
            } else if (choice == 5) {
                double totalRevenue = repo.findAll().stream().mapToDouble(Order::getFinalAmount).sum();
                System.out.println("Tổng doanh thu: " + (long)totalRevenue);
            } else if (choice == 0) break;
        }
    }
}
