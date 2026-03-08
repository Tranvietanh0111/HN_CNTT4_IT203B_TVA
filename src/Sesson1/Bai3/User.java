package Sesson1.Bai3;
public class User {
    private String name;
    private int age;

    public User(String name) {
        this.name = name;
    }

    // Phương thức setter bảo vệ logic nghiệp vụ
    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không thể âm! Giá trị nhập vào là: " + age);
        }
        this.age = age;
        System.out.println("Cập nhật tuổi thành công: " + age);
    }

    public int getAge() {
        return age;
    }
}