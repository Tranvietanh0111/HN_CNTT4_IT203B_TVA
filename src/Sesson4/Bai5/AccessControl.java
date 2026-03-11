package Sesson4.Bai5;
public class AccessControl {
    public enum Role { ADMIN, MODERATOR, USER }
    public enum Action { DELETE_USER, LOCK_USER, VIEW_PROFILE }

    public boolean canPerformAction(Role role, Action action) {
        if (role == null || action == null) return false;

        switch (role) {
            case ADMIN:
                return true; // ADMIN có mọi quyền
            case MODERATOR:
                return action == Action.LOCK_USER || action == Action.VIEW_PROFILE;
            case USER:
                return action == Action.VIEW_PROFILE;
            default:
                return false;
        }
    }
}