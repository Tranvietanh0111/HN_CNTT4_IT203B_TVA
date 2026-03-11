package Sesson4.Bai6;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private UserService userService;
    private User existingUser;
    private List<User> allUsers;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        existingUser = new User("old@mail.com", LocalDate.of(2000,1,1));
        allUsers = new ArrayList<>();
    }
    @Test
    void updateProfile_validEmailAndBirthDate_shouldUpdateSuccessfully() {

        UserProfile newProfile =
                new UserProfile("new@mail.com", LocalDate.of(2001,1,1));

        User result = userService.updateProfile(existingUser, newProfile, allUsers);

        assertNotNull(result);
        assertEquals("new@mail.com", result.getEmail());
        assertEquals(LocalDate.of(2001,1,1), result.getBirthDate());
    }
    @Test
    void updateProfile_futureBirthDate_shouldRejectUpdate() {

        UserProfile newProfile =
                new UserProfile("new@mail.com", LocalDate.now().plusDays(1));

        User result = userService.updateProfile(existingUser, newProfile, allUsers);

        assertNull(result);
    }
    @Test
    void updateProfile_duplicateEmail_shouldRejectUpdate() {

        allUsers.add(new User("duplicate@mail.com", LocalDate.of(1999,1,1)));

        UserProfile newProfile =
                new UserProfile("duplicate@mail.com", LocalDate.of(2001,1,1));

        User result = userService.updateProfile(existingUser, newProfile, allUsers);

        assertNull(result);
    }
    @Test
    void updateProfile_sameEmail_shouldAllowUpdate() {

        UserProfile newProfile =
                new UserProfile("old@mail.com", LocalDate.of(2002,2,2));

        User result = userService.updateProfile(existingUser, newProfile, allUsers);

        assertNotNull(result);
        assertEquals(LocalDate.of(2002,2,2), result.getBirthDate());
    }
    @Test
    void updateProfile_validEmailButEmptyUserList_shouldUpdateSuccessfully() {

        UserProfile newProfile =
                new UserProfile("another@mail.com", LocalDate.of(2001,1,1));

        User result = userService.updateProfile(existingUser, newProfile, allUsers);

        assertNotNull(result);
    }
    @Test
    void updateProfile_duplicateEmailAndFutureBirthDate_shouldRejectUpdate() {

        allUsers.add(new User("duplicate@mail.com", LocalDate.of(1999,1,1)));

        UserProfile newProfile =
                new UserProfile("duplicate@mail.com", LocalDate.now().plusDays(5));

        User result = userService.updateProfile(existingUser, newProfile, allUsers);

        assertNull(result);
    }
}