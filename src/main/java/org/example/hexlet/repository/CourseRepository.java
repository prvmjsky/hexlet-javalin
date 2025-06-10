package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.Getter;
import org.example.hexlet.model.Course;

public class CourseRepository {
    @Getter
    private static final List<Course> entities = new ArrayList<Course>();

    public static void save(Course course) {
        course.setId((long) entities.size() + 1);
        entities.add(course);
    }

    public static List<Course> search(String term) {
        return entities.stream()
            .filter(entity -> entity.getName().startsWith(term))
            .toList();
    }

    public static Optional<Course> find(Long id) {
        return entities.stream()
            .filter(entity -> entity.getId() == id)
            .findAny();
    }

    public static void fillWithFakes() {
        var course1 = new Course("Java", "A damn fine cup of coffee");
        var course2 = new Course("Python", "A long one");
        var course3 = new Course("PHP", "ZXC");

        save(course1);
        save(course2);
        save(course3);
    }
}