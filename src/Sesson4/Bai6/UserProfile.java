package Sesson4.Bai6;

import java.time.LocalDate;

public class UserProfile {
    private String email;
    private LocalDate birthDate;

    public UserProfile(String email, LocalDate birthDate) {
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
