package org.example.hexlet.repository;

import lombok.Getter;
import org.example.hexlet.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    @Getter
    private static final List<User> entities = new ArrayList<>();

    public static void save(User user) {
        user.setId((long) entities.size() + 1);
        entities.add(user);
    }

    public static List<User> search(String term) {
        return entities.stream()
            .filter(entity -> entity.getName().startsWith(term))
            .toList();
    }

    public static Optional<User> find(Long id) {
        return entities.stream()
            .filter(entity -> entity.getId() == id)
            .findAny();
    }

    public static void fillWithFakes() {
        var sam = new User("Sam", "ogogo@outlook.com", "123");
        var tom = new User("Tom", "go@gmail.com", "213");
        var pam = new User("Pam", "aga@mail.ru", "312");

        save(sam);
        save(tom);
        save(pam);
    }
}