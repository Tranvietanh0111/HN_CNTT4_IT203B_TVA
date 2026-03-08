package Sesson1.Bai5;
public class User {
    private String username;
    private int age;

    public User(String username) {
        this.username = username;
    }

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 150) {
            throw new InvalidAgeException("Lỗi nghiệp vụ: Tuổi (" + age + ") không hợp lệ. Tuổi phải từ 0 đến 150.");
        }
        this.age = age;
        System.out.println("Cập nhật tuổi cho " + username + " thành công: " + age);
    }
}