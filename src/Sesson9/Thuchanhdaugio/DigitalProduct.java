package Sesson9.Thuchanhdaugio;

public class DigitalProduct extends Product {
    private double size;

    public DigitalProduct(String id, String name, double price, double size) {
        super(id, name, price);
        this.size = size;
    }

    public void setSize(double size) { this.size = size; }

    @Override
    public void displayInfo() {
        System.out.printf("[Digital]  ID: %-5s | Tên: %-15s | Giá: %-10.2f | Dung lượng: %.2f MB\n",
                id, name, price, size);
    }
}