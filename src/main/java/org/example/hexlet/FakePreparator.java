package org.example.hexlet;

import org.example.hexlet.model.Course;
import org.example.hexlet.model.Post;
import org.example.hexlet.model.User;

import java.util.List;

public class FakePreparator {
    public static List<User> prepareFakeUsers() {
        var sam = new User(1L, "Sam");
        var tom = new User(2L, "Tom");
        var pam = new User(3L, "Pam");

        return List.of(sam, tom, pam);
    }

    public static List<Post> prepareFakePosts() {
        var first = new Post(1L, "About me", "Hi! I'm Sam.");
        var second = new Post(2L, "About me", "Hi! I'm Tom.");
        var third = new Post(3L, "About me", "Hi! I'm Pam.");

        return List.of(first, second, third);
    }

    public static List<Course> prepareFakeCourses() {
        var course1 = new Course(1L, "Java", "A damn fine cup of coffee");
        var course2 = new Course(2L, "Python", "A long one");
        var course3 = new Course(3L, "PHP", "ZXC");

        return List.of(course1, course2, course3);
    }
}
