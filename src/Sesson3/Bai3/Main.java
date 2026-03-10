package Sesson3.Bai3;

import java.util.List;
import java.util.Optional;

public class Main {
    enum Status { ACTIVE, INACTIVE }
    record User(String username, String email, Status status) {}

    static class UserRepository {
        private static final List<User> users = List.of(
                new User("alice", "alice@gmail.com", Status.ACTIVE),
                new User("bob", "bob@yahoo.com", Status.INACTIVE),
                new User("charlie", "charlie@gmail.com", Status.ACTIVE)
        );

        public static Optional<User> findUserByUsername(String username) {
            return users.stream()
                    .filter(u -> u.username().equalsIgnoreCase(username))
                    .findFirst();
        }
    }

    public static void main(String[] args) {
        checkLogin("alice");
        checkLogin("unknown_user");
    }

    private static void checkLogin(String username) {
        System.out.print("Searching for '" + username + "': ");

        UserRepository.findUserByUsername(username)
                .ifPresentOrElse(
                        user -> System.out.println("Welcome " + user.username()),
                        () -> System.out.println("Guest login")
                );
    }
}
