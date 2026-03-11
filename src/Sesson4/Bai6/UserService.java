package Sesson4.Bai6;
import java.time.LocalDate;
import java.util.List;

public class UserService {
    public User updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {
        if (newProfile.getBirthDate().isAfter(LocalDate.now())) {
            return null;
        }
        String newEmail = newProfile.getEmail();

        if (!newEmail.equals(existingUser.getEmail())) {

            for (User user : allUsers) {
                if (user.getEmail().equals(newEmail)) {
                    return null;
                }
            }
        }
        existingUser.setEmail(newEmail);
        existingUser.setBirthDate(newProfile.getBirthDate());
        return existingUser;
    }
}
