package Sesson8.Bai6;

import java.util.*;

interface DiscountStrategy {
    double calculateDiscount(double amount);
    String getDescription();
}

interface PaymentMethod {
    void process(double amount);
}

interface NotificationService {
    void send(String message);
}

interface SalesChannelFactory {
    DiscountStrategy createDiscountStrategy();
    PaymentMethod createPaymentMethod();
    NotificationService createNotificationService();
    String getChannelName();
}

class WebsiteDiscount implements DiscountStrategy {
    @Override public double calculateDiscount(double amount) { return amount * 0.10; }
    @Override public String getDescription() { return "Áp dụng giảm giá 10% (Website)"; }
}

class CreditCardPayment implements PaymentMethod {
    @Override public void process(double amount) { System.out.println("Xử lý thanh toán thẻ tín dụng: " + (long)amount); }
}

class EmailNotification implements NotificationService {
    @Override public void send(String message) { System.out.println("Gửi email: " + message); }
}

class WebsiteFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscountStrategy() { return new WebsiteDiscount(); }
    @Override public PaymentMethod createPaymentMethod() { return new CreditCardPayment(); }
    @Override public NotificationService createNotificationService() { return new EmailNotification(); }
    @Override public String getChannelName() { return "Website"; }
}

class FirstTimeDiscount implements DiscountStrategy {
    @Override public double calculateDiscount(double amount) { return amount * 0.15; }
    @Override public String getDescription() { return "Áp dụng giảm giá 15% (Lần đầu mua trên Mobile)"; }
}

class MomoPayment implements PaymentMethod {
    @Override public void process(double amount) { System.out.println("Xử lý thanh toán MoMo: " + (long)amount); }
}

class PushNotification implements NotificationService {
    @Override public void send(String message) { System.out.println("Gửi push notification: " + message); }
}

class MobileAppFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscountStrategy() { return new FirstTimeDiscount(); }
    @Override public PaymentMethod createPaymentMethod() { return new MomoPayment(); }
    @Override public NotificationService createNotificationService() { return new PushNotification(); }
    @Override public String getChannelName() { return "Mobile App"; }
}

class MemberDiscount implements DiscountStrategy {
    @Override public double calculateDiscount(double amount) { return amount * 0.05; }
    @Override public String getDescription() { return "Áp dụng giảm giá 5% (Thành viên POS)"; }
}

class CODPayment implements PaymentMethod {
    @Override public void process(double amount) { System.out.println("Xử lý thanh toán COD tại quầy: " + (long)amount); }
}

class PrintReceipt implements NotificationService {
    @Override public void send(String message) { System.out.println("In hóa đơn tại quầy: " + message); }
}

class POSFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscountStrategy() { return new MemberDiscount(); }
    @Override public PaymentMethod createPaymentMethod() { return new CODPayment(); }
    @Override public NotificationService createNotificationService() { return new PrintReceipt(); }
    @Override public String getChannelName() { return "Store POS"; }
}

class OrderService {
    private final SalesChannelFactory factory;

    public OrderService(SalesChannelFactory factory) {
        this.factory = factory;
    }

    public void processOrder(String productName, double price, int quantity) {
        double total = price * quantity;
        DiscountStrategy discount = factory.createDiscountStrategy();
        PaymentMethod payment = factory.createPaymentMethod();
        NotificationService notify = factory.createNotificationService();

        double discountVal = discount.calculateDiscount(total);
        double finalAmount = total - discountVal;

        System.out.println("--- Đơn hàng " + factory.getChannelName() + " ---");
        System.out.println("Sản phẩm: " + productName + " - SL: " + quantity);
        System.out.println(discount.getDescription() + ": " + (long)discountVal);
        payment.process(finalAmount);
        notify.send("Đơn hàng thành công");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Website | 2. Mobile App | 3. POS | 0. Thoát");
            System.out.print("Chọn kênh: ");
            String choice = sc.nextLine();

            SalesChannelFactory factory = null;
            if (choice.equals("1")) factory = new WebsiteFactory();
            else if (choice.equals("2")) factory = new MobileAppFactory();
            else if (choice.equals("3")) factory = new POSFactory();
            else if (choice.equals("0")) break;
            else continue;

            System.out.println("Bạn đã chọn kênh " + factory.getChannelName());
            System.out.print("Sản phẩm: "); String name = sc.nextLine();
            System.out.print("Giá: "); double price = Double.parseDouble(sc.nextLine());
            System.out.print("Số lượng: "); int qty = Integer.parseInt(sc.nextLine());

            OrderService service = new OrderService(factory);
            service.processOrder(name, price, qty);
        }
        sc.close();
    }
}