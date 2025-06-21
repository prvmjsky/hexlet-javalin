package org.example.hexlet.repository;

import lombok.Getter;
import org.example.hexlet.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostRepository extends BaseRepository {
    @Getter
    private static final List<Post> entities = new ArrayList<>();

    public static void save(Post post) {
        post.setId((long) entities.size() + 1);
        entities.add(post);
    }

    public static List<Post> search(String term) {
        return entities.stream()
            .filter(entity -> entity.getTitle().startsWith(term))
            .toList();
    }

    public static Optional<Post> find(Long id) {
        return entities.stream()
            .filter(entity -> entity.getId() == id)
            .findAny();
    }

    public static void fillWithFakes() {
        var first = new Post("About me", "Hi! I'm Sam.");
        var second = new Post("About me", "Hi! I'm Tom.");
        var third = new Post("About me", "Hi! I'm Pam.");

        save(first);
        save(second);
        save(third);
    }
}