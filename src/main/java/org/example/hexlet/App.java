package org.example.hexlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.controller.CoursesController;
import org.example.hexlet.controller.UsersController;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.PostRepository;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.util.NamedRoutes;

import java.time.Instant;

public class App {

    public static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        UserRepository.fillWithFakes();
        CourseRepository.fillWithFakes();
        PostRepository.fillWithFakes();

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.before(ctx -> LOG.info(Instant.now().toString()));

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get(NamedRoutes.welcomingPath(), ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result(String.format("Hello, %s!", name));
        });

        app.get(NamedRoutes.usersPath(), UsersController::index);

        app.post(NamedRoutes.usersPath(), UsersController::create);

        app.get(NamedRoutes.buildUserPath(), UsersController::build);

        app.get(NamedRoutes.userPath("{id}"), UsersController::show);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);

        app.post(NamedRoutes.coursesPath(), CoursesController::create);

        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);

        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);

        app.get("/users/{id}/posts/{postId}", ctx -> {
            var userId = ctx.pathParamAsClass("id", Long.class).get();
            var postId = ctx.pathParamAsClass("postId", Long.class).get();

            var user = UserRepository.find(userId).orElseThrow(NotFoundResponse::new);
            var post = PostRepository.find(postId).orElseThrow(NotFoundResponse::new);

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
}
