package Sesson3.Bai6;


import java.util.List;
import java.util.stream.Collectors;

public class Main {
    record Post(String title, List<String> tags) {}

    public static void main(String[] args) {
        List<Post> posts = List.of(
                new Post("Java Tutorial", List.of("java", "backend")),
                new Post("Python for Data", List.of("python", "data")),
                new Post("Web Basics", List.of("html", "css", "frontend"))
        );

        List<String> allTags = posts.stream()
                .flatMap(post -> post.tags().stream())
                .collect(Collectors.toList());
        System.out.println("--- Danh sách tags đã được làm phẳng ---");
        System.out.println(allTags);
    }
}