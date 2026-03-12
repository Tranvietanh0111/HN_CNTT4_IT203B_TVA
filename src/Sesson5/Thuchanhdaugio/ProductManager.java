package Sesson5.Thuchanhdaugio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    public void addProduct(Product p) {
        boolean isExisted = products.stream().anyMatch(item -> item.getId() == p.getId());
        if (isExisted) {
            throw new InvalidProductException("ID " + p.getId() + " đã tồn tại trong kho");
        }
        products.add(p);
        System.out.println("Thêm sản phẩm thành công!");
    }
    public void displayAll() {
        if (products.isEmpty()) {
            System.out.println("Danh sách sản phẩm đang trống.");
            return;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("| ID    | Tên Sản Phẩm    | Giá        | Số lượng | Danh mục     |");
        System.out.println("------------------------------------------------------------------");
        products.forEach(p -> System.out.println(p.toString()));
    }

    // Cập nhật: Tìm sản phẩm bằng Stream & Optional
    public void updateQuantity(int id, int newQuantity) {
        Optional<Product> foundProduct = products.stream()
                .filter(p -> p.getId() == id)
                .findFirst();

        if (foundProduct.isPresent()) {
            foundProduct.get().setQuantity(newQuantity);
            System.out.println("Cập nhật só lượng sản phẩm thành công");
        } else {
            throw new InvalidProductException("không tìm thấy sản phẩm có ID: " + id);
        }
    }
    public void deleteOutOfStock() {
        boolean removed = products.removeIf(p -> p.getQuantity() == 0);
        if (removed) {
            System.out.println("Đã xóa sản phẩm có số lượng bằng 0.");
        } else {
            System.out.println("Không có sản phẩm hết hàng.");
        }
    }
}