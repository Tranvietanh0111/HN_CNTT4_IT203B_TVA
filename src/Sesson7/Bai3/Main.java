package Sesson7.Bai3;

interface PaymentMethod {
    void processPayment(double amount);
}

interface CODPayable extends PaymentMethod {
    void processCOD(double amount);
}

interface CardPayable extends PaymentMethod {
    void processCreditCard(double amount);
}

interface EWalletPayable extends PaymentMethod {
    void processMomo(double amount);
}

class CODPayment implements CODPayable {
    @Override
    public void processPayment(double amount) {
        processCOD(amount);
    }

    @Override
    public void processCOD(double amount) {
        System.out.println("Xử lý thanh toán COD: " + (long)amount + " - Thành công");
    }
}

class CreditCardPayment implements CardPayable {
    @Override
    public void processPayment(double amount) {
        processCreditCard(amount);
    }

    @Override
    public void processCreditCard(double amount) {
        System.out.println("Xử lý thanh toán thẻ tín dụng: " + (long)amount + " - Thành công");
    }
}

class MomoPayment implements EWalletPayable {
    @Override
    public void processPayment(double amount) {
        processMomo(amount);
    }

    @Override
    public void processMomo(double amount) {
        System.out.println("Xử lý thanh toán MoMo: " + (long)amount + " - Thành công");
    }
}

class PaymentProcessor {
    public void execute(PaymentMethod paymentMethod, double amount) {
        paymentMethod.processPayment(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        PaymentMethod cod = new CODPayment();
        System.out.print("COD | ");
        processor.execute(cod, 500000);

        PaymentMethod card = new CreditCardPayment();
        System.out.print("Thẻ tín dụng | ");
        processor.execute(card, 1000000);

        PaymentMethod momo = new MomoPayment();
        System.out.print("Ví MoMo | ");
        processor.execute(momo, 750000);

        System.out.println("\n--- Kiểm tra LSP ---");
        PaymentMethod testLSP = new CreditCardPayment();
        System.out.print("Đang dùng Card: ");
        processor.execute(testLSP, 100000);

        testLSP = new MomoPayment();
        System.out.print("Thay thế bằng Momo: ");
        processor.execute(testLSP, 100000);
    }
}
