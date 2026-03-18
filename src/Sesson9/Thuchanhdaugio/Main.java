package Sesson9.Thuchanhdaugio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("\n==QUẢN LÝ SẢN PHẨM==");
            System.out.println("1.Thêm mới sản phẩm");
            System.out.println("2.Xem danh sách sản phẩm");
            System.out.println("3.Cập nhật thông tin sản phẩm");
            System.out.println("4.Xoá sản phẩm");
            System.out.println("5.Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Loại 1: Vật lý, 2: Kỹ thuật số: ");
                        int type = Integer.parseInt(sc.nextLine());
                        System.out.print("ID: "); String id = sc.nextLine();
                        System.out.print("Tên: "); String name = sc.nextLine();
                        System.out.print("Giá: "); double price = Double.parseDouble(sc.nextLine());
                        System.out.print(type == 1 ? "Trọng lượng (kg): " : "Dung lượng (MB): ");
                        double extra = Double.parseDouble(sc.nextLine());

                        Product newP = ProductFactory.createProduct(type, id, name, price, extra);
                        if (newP != null) {
                            db.addProduct(newP);
                            System.out.println(" Thêm thành công");
                        } else System.out.println("loại sản phẩm không hợp lệ");
                        break;

                    case 2:
                        System.out.println("\n==DANH SÁCH TRONG KHO==");
                        if (db.getAllProducts().isEmpty()) System.out.println("Trống.");
                        else db.getAllProducts().forEach(Product::displayInfo);
                        break;

                    case 3:
                        System.out.print("Nhập ID sản phẩm cần sửa: ");
                        Product p = db.findById(sc.nextLine());
                        if (p != null) {
                            System.out.print("Tên mới: "); p.setName(sc.nextLine());
                            System.out.print("Giá mới: "); p.setPrice(Double.parseDouble(sc.nextLine()));
                            if (p instanceof PhysicalProduct) {
                                System.out.print("Trọng lượng mới: ");
                                ((PhysicalProduct) p).setWeight(Double.parseDouble(sc.nextLine()));
                            } else {
                                System.out.print("Dung lượng mới: ");
                                ((DigitalProduct) p).setSize(Double.parseDouble(sc.nextLine()));
                            }
                            System.out.println(" Cập nhật thành công");
                        } else System.out.println(" Không thấy sản phẩm");
                        break;

                    case 4:
                        System.out.print("Nhập ID cần xoá: ");
                        if (db.deleteProduct(sc.nextLine())) System.out.println("Xoá  thành công");
                        else System.out.println("Khôngthấy ID");
                        break;

                    case 5:
                        System.out.println("Đang thoát hệ thống");
                        return;

                    default:
                        System.out.println("Lựa chọn 1-5");
                }
            } catch (Exception e) {
                System.out.println(" Vui lòng thử lại");
            }
        }
    }
}