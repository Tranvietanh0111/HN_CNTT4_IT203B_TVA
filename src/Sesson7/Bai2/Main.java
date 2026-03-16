package Sesson7.Bai2;

import java.util.Map;
import java.util.HashMap;

interface DiscountStrategy {
    double applyDiscount(double totalAmount);
}

class PercentageDiscount implements DiscountStrategy {
    private double percentage;

    public PercentageDiscount(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount * (1 - percentage / 100);
    }
}

class FixedDiscount implements DiscountStrategy {
    private double amount;

    public FixedDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - amount;
    }
}

class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount;
    }
}

class HolidayDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount * 0.85;
    }
}

class OrderCalculator {
    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public double calculateFinalAmount(double totalAmount) {
        if (discountStrategy == null) {
            return totalAmount;
        }
        return discountStrategy.applyDiscount(totalAmount);
    }
}

public class Main {
    public static void main(String[] args) {
        double totalAmount = 1000000;
        OrderCalculator calculator = new OrderCalculator();

        calculator.setDiscountStrategy(new PercentageDiscount(10));
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng PercentageDiscount 10%");
        System.out.println("Số tiền sau giảm: " + (long)calculator.calculateFinalAmount(totalAmount));

        calculator.setDiscountStrategy(new FixedDiscount(50000));
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng FixedDiscount 50.000");
        System.out.println("Số tiền sau giảm: " + (long)calculator.calculateFinalAmount(totalAmount));

        calculator.setDiscountStrategy(new NoDiscount());
        System.out.println("Đơn hàng: tổng tiền 1.000.000, áp dụng NoDiscount");
        System.out.println("Số tiền sau giảm: " + (long)calculator.calculateFinalAmount(totalAmount));

        calculator.setDiscountStrategy(new HolidayDiscount());
        System.out.println("Thêm HolidayDiscount 15% (không sửa code cũ)");
        System.out.println("Số tiền sau giảm: " + (long)calculator.calculateFinalAmount(totalAmount));
    }
}