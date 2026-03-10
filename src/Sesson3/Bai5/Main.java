package Sesson3.Bai5;


import java.util.Comparator;
import java.util.List;

public class Main {

    enum Status { ACTIVE, INACTIVE }
    record User(String username, String email, Status status) {}

    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alex", "alex@gmail.com", Status.ACTIVE),
                new User("alexander", "alexander@gmail.com", Status.ACTIVE),
                new User("bob", "bob@yahoo.com", Status.INACTIVE),
                new User("charlotte", "charlotte@gmail.com", Status.ACTIVE),
                new User("benjamin", "benjamin@gmail.com", Status.ACTIVE),
                new User("dan", "dan@gmail.com", Status.ACTIVE)
        );

        System.out.println("--- Top 3 người dùng có Username dài nhất ---");
        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
                .forEach(u -> System.out.println(u.username() + " (" + u.username().length() + " ký tự)"));
    }
}