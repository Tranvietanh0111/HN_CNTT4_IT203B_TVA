package Sesson7.Bai6;

import java.util.*;

interface DiscountStrategy {
    double apply(double amount);
    String getDescription();
}

interface PaymentMethod {
    void process(double amount);
    String getName();
}

interface NotificationService {
    void notifyUser(String message);
}

interface SalesChannelFactory {
    DiscountStrategy createDiscount();
    PaymentMethod createPayment();
    NotificationService createNotification();
    String getChannelName();
}

class WebsiteDiscount implements DiscountStrategy {
    @Override public double apply(double amount) { return amount * 0.10; }
    @Override public String getDescription() { return "Áp dụng giảm giá 10% cho đơn hàng website (WEB10)"; }
}

class OnlineCardPayment implements PaymentMethod {
    @Override public void process(double amount) { System.out.println("Xử lý thanh toán thẻ tín dụng qua cổng thanh toán online: " + (long)amount); }
    @Override public String getName() { return "Thẻ tín dụng Online"; }
}

class EmailNotification implements NotificationService {
    @Override public void notifyUser(String message) { System.out.println("Gửi email xác nhận: " + message); }
}

class WebsiteFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscount() { return new WebsiteDiscount(); }
    @Override public PaymentMethod createPayment() { return new OnlineCardPayment(); }
    @Override public NotificationService createNotification() { return new EmailNotification(); }
    @Override public String getChannelName() { return "Website"; }
}

class MobileFirstOrderDiscount implements DiscountStrategy {
    @Override public double apply(double amount) { return amount * 0.15; }
    @Override public String getDescription() { return "Áp dụng giảm giá 15% cho lần đầu mua trên Mobile"; }
}

class MomoPayment implements PaymentMethod {
    @Override public void process(double amount) { System.out.println("Xử lý thanh toán MoMo tích hợp: " + (long)amount); }
    @Override public String getName() { return "Ví MoMo"; }
}

class PushNotification implements NotificationService {
    @Override public void notifyUser(String message) { System.out.println("Gửi push notification: " + message); }
}

class MobileAppFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscount() { return new MobileFirstOrderDiscount(); }
    @Override public PaymentMethod createPayment() { return new MomoPayment(); }
    @Override public NotificationService createNotification() { return new PushNotification(); }
    @Override public String getChannelName() { return "Mobile App"; }
}

class MemberDiscount implements DiscountStrategy {
    @Override public double apply(double amount) { return 50000; }
    @Override public String getDescription() { return "Giảm cố định 50.000đ cho thành viên tại quầy"; }
}

class CashPayment implements PaymentMethod {
    @Override public void process(double amount) { System.out.println("Xử lý thanh toán tiền mặt tại quầy: " + (long)amount); }
    @Override public String getName() { return "Tiền mặt"; }
}

class PrintReceipt implements NotificationService {
    @Override public void notifyUser(String message) { System.out.println("Đang in hóa đơn giấy: " + message); }
}

class StorePOSFactory implements SalesChannelFactory {
    @Override public DiscountStrategy createDiscount() { return new MemberDiscount(); }
    @Override public PaymentMethod createPayment() { return new CashPayment(); }
    @Override public NotificationService createNotification() { return new PrintReceipt(); }
    @Override public String getChannelName() { return "Store POS"; }
}

class OrderService {
    public void processOrder(String product, double price, SalesChannelFactory factory) {
        System.out.println("\n--- XỬ LÝ ĐƠN HÀNG TRÊN KÊNH: " + factory.getChannelName().toUpperCase() + " ---");

        DiscountStrategy discount = factory.createDiscount();
        PaymentMethod payment = factory.createPayment();
        NotificationService notification = factory.createNotification();

        double discountAmount = discount.apply(price);
        double finalAmount = price - discountAmount;

        System.out.println("Sản phẩm: " + product);
        System.out.println(discount.getDescription());
        payment.process(finalAmount);
        notification.notifyUser("Đơn hàng " + product + " thành công!");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderService orderService = new OrderService();
        SalesChannelFactory factory = null;

        while (true) {
            System.out.println("\n1. Website | 2. Mobile App | 3. Store POS | 0. Thoát");
            System.out.print("Chọn kênh bán hàng: ");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                factory = new WebsiteFactory();
            } else if (choice.equals("2")) {
                factory = new MobileAppFactory();
            } else if (choice.equals("3")) {
                factory = new StorePOSFactory();
            } else if (choice.equals("0")) {
                break;
            } else {
                continue;
            }

            System.out.println("Bạn đã chọn kênh " + factory.getChannelName());
            System.out.print("Nhập tên sản phẩm: ");
            String product = sc.nextLine();
            System.out.print("Nhập giá: ");
            double price = Double.parseDouble(sc.nextLine());

            orderService.processOrder(product, price, factory);
        }
        sc.close();
    }
}