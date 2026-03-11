package Sesson4.Bai5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Sesson4.Bai5.AccessControl.Role;
import Sesson4.Bai5.AccessControl.Action;

class AccessControlTest {

    private AccessControl ac;

    @BeforeEach
    void setUp() {
        ac = new AccessControl();
    }

    @AfterEach
    void tearDown() {
        ac = null; // Dọn dẹp đối tượng sau mỗi bài test
    }

    @Test
    void testAdminPermissions() {
        assertAll("ADMIN nên có toàn quyền",
                () -> assertTrue(ac.canPerformAction(Role.ADMIN, Action.DELETE_USER)),
                () -> assertTrue(ac.canPerformAction(Role.ADMIN, Action.LOCK_USER)),
                () -> assertTrue(ac.canPerformAction(Role.ADMIN, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testModeratorPermissions() {
        assertAll("MODERATOR chỉ có quyền khóa và xem",
                () -> assertFalse(ac.canPerformAction(Role.MODERATOR, Action.DELETE_USER)),
                () -> assertTrue(ac.canPerformAction(Role.MODERATOR, Action.LOCK_USER)),
                () -> assertTrue(ac.canPerformAction(Role.MODERATOR, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testUserPermissions() {
        assertAll("USER chỉ có quyền xem profile",
                () -> assertFalse(ac.canPerformAction(Role.USER, Action.DELETE_USER)),
                () -> assertFalse(ac.canPerformAction(Role.USER, Action.LOCK_USER)),
                () -> assertTrue(ac.canPerformAction(Role.USER, Action.VIEW_PROFILE))
        );
    }
}