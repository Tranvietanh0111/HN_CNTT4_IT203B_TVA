package Sesson5.Thuchanhdaugio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        do {
            System.out.println("\n========= PRODUCT MANAGEMENT SYSTEM =========");
            System.out.println("1. Thêm sản phẩm mới");
            System.out.println("2. Hiển thị danh sách sản phẩm");
            System.out.println("3. Cập nhật số lượng theo ID");
            System.out.println("4. Xóa sản phẩm đã hết hàng (Qty=0)");
            System.out.println("0. Thoát chương trình");
            System.out.print("===================================================");
            System.out.print("Lựa chọn của bạn: ");
            try {
                String input = sc.nextLine();
                choice = Integer.parseInt(input);
                switch (choice) {
                    case 1:
                        System.out.print("Nhập ID: ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập tên sản phẩm: ");
                        String name = sc.nextLine();
                        System.out.print("Nhập giá: ");
                        double price = Double.parseDouble(sc.nextLine());
                        System.out.print("Nhập số lượng: ");
                        int qty = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập danh mục: ");
                        String category = sc.nextLine();
                        manager.addProduct(new Product(id, name, price, qty, category));
                        break;
                    case 2:
                        manager.displayAll();
                        break;
                    case 3:
                        System.out.print("Nhập ID cần cập nhật: ");
                        int uId = Integer.parseInt(sc.nextLine());
                        System.out.print("Nhập số lượng mới: ");
                        int uQty = Integer.parseInt(sc.nextLine());
                        manager.updateQuantity(uId, uQty);
                        break;
                    case 4:
                        manager.deleteOutOfStock();
                        break;
                    case 0:
                        System.out.println("Đang thoát");
                        break;
                    default:
                        System.out.println("Lựa chọn từ 0-4");
                }
            } catch (NumberFormatException e) {
                System.out.println("nhập đúng định dạng số");
            } catch (InvalidProductException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(" lỗi không xác định");
            }
        } while (choice != 0);

        sc.close();
    }
}