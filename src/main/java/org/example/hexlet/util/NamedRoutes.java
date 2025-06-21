package org.example.hexlet.util;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }
    public static String welcomingPath() {
        return "/hello";
    }

    public static String sessionsPath() {
        return "/sessions";
    }
    public static String buildSessionPath() {
        return "/sessions/build";
    }

    public static String usersPath() {
        return "/users";
    }
    public static String buildUserPath() {
        return "/users/build";
    }
    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }
    public static String userPath(String id) {
        return "/users/" + id;
    }

    public static String coursesPath() {
        return "/courses";
    }
    public static String buildCoursePath() {
        return "/courses/build";
    }
    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }
    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    public static String userPostsPath(Long userId) {
        return userPostsPath(String.valueOf(userId));
    }
    public static String userPostsPath(String userId) {
        return "/users/" + userId + "/posts/";
    }
    public static String userPostPath(Long userId, Long postId) {
        return userPostPath(String.valueOf(userId), String.valueOf(postId));
    }
    public static String userPostPath(String userId, String postId) {
        return "/users/" + userId + "/posts/" + postId;
    }
}
