package Sesson3.Bai4;


import java.util.*;
import java.util.stream.Collectors;

public class Main {
    enum Status { ACTIVE, INACTIVE }
    record User(String username, String email, Status status) {}

    public static void main(String[] args) {
        List<User> rawUsers = List.of(
                new User("alice", "alice@gmail.com", Status.ACTIVE),
                new User("bob", "bob@yahoo.com", Status.INACTIVE),
                new User("alice", "alice_new@gmail.com", Status.ACTIVE),
                new User("charlie", "charlie@gmail.com", Status.ACTIVE),
                new User("bob", "bob_2@yahoo.com", Status.ACTIVE)
        );

        System.out.println("--- Danh sách ban đầu (" + rawUsers.size() + " users) ---");
        rawUsers.forEach(System.out::println);
        Collection<User> uniqueUsersMap = rawUsers.stream()
                .collect(Collectors.toMap(
                        User::username,
                        user -> user,
                        (existing, replacement) -> existing
                ))
                .values();

        System.out.println("\n--- Cách 1: Sau khi lọc bằng toMap (Giữ bản ghi đầu) ---");
        uniqueUsersMap.forEach(System.out::println);
        List<User> uniqueUsersSet = rawUsers.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(User::username))),
                        ArrayList::new
                ));

        System.out.println("\n--- Cách 2: Sau khi lọc bằng TreeSet (Sắp xếp theo tên) ---");
        uniqueUsersSet.forEach(System.out::println);
    }
}