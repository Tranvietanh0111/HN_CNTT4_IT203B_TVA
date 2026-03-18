package Sesson9.Thuchanhdaugio;

public class PhysicalProduct extends Product {
    private double weight;

    public PhysicalProduct(String id, String name, double price, double weight) {
        super(id, name, price);
        this.weight = weight;
    }

    public void setWeight(double weight) { this.weight = weight; }

    @Override
    public void displayInfo() {
        System.out.printf("[Physical] ID: %-5s | Tên: %-15s | Giá: %-10.2f | Nặng: %.2f kg\n",
                id, name, price, weight);
    }
}