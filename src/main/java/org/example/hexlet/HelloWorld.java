package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.model.Post;
import org.example.hexlet.model.User;

import java.util.Map;

public class HelloWorld {
    public static void main(String[] args) {
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result(String.format("Hello, %s!", name));
        });


        app.get("/users/{id}/posts/{postId}", ctx -> {
            var userId = ctx.pathParamAsClass("id", Long.class).get();
            var postId = ctx.pathParamAsClass("postId", Long.class).get();
            var user = prepareFakeUsers().get(userId);
            var post = prepareFakePosts().get(postId);
            user.addPost(post);

            ctx.result(String.format("""
                User: %s
                Last post:
                "%s"
                
                %s
                """, user.getName(), post.getTitle(), post.getContent()));
        });

        app.start(7070);
    }

    private static Map<Long, User> prepareFakeUsers() {
        var sam = new User(1L, "Sam");
        var tom = new User(2L, "Tom");
        var pam = new User(3L, "Pam");

        return Map.of(1L, sam, 2L, tom, 3L, pam);
    }

    private static Map<Long, Post> prepareFakePosts() {
        var first = new Post(1L, "About me", "Hi! I'm Sam.");
        var second = new Post(2L, "About me", "Hi! I'm Tom.");
        var third = new Post(3L, "About me", "Hi! I'm Pam.");

        return Map.of(1L, first, 2L, second, 3L, third);
    }
}
