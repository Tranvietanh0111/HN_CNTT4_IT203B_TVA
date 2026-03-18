package Sesson9.Thuchanhdaugio;

import java.util.ArrayList;
import java.util.List;

public class ProductDatabase {
    private static ProductDatabase instance;
    private List<Product> products;

    private ProductDatabase() {
        products = new ArrayList<>();
    }

    public static ProductDatabase getInstance() {
        if (instance == null) {
            instance = new ProductDatabase();
        }
        return instance;
    }

    public void addProduct(Product p) { products.add(p); }

    public List<Product> getAllProducts() { return products; }

    public Product findById(String id) {
        for (Product p : products) {
            if (p.getId().equalsIgnoreCase(id)) return p;
        }
        return null;
    }

    public boolean deleteProduct(String id) {
        return products.removeIf(p -> p.getId().equalsIgnoreCase(id));
    }
}