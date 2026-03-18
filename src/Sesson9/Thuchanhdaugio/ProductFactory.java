package Sesson9.Thuchanhdaugio;

public class ProductFactory {
    public static Product createProduct(int type, String id, String name, double price, double extraValue) {
        if (type == 1) {
            return new PhysicalProduct(id, name, price, extraValue);
        } else if (type == 2) {
            return new DigitalProduct(id, name, price, extraValue);
        }
        return null;
    }
}