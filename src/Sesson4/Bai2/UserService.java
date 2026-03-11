package Sesson4.Bai2;
public class UserService {

    public boolean checkRegistrationAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Tuổi không thể là số âm");
        }
        return age >= 18;
    }
}
