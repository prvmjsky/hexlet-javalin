package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.PostRepository;
import org.example.hexlet.repository.UserRepository;

import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        UserRepository.fillWithFakes();
        CourseRepository.fillWithFakes();
        PostRepository.fillWithFakes();

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result(String.format("Hello, %s!", name));
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.find(id).orElseThrow(NotFoundResponse::new);
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        });

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

        app.get("/courses", ctx -> {
            var term = ctx.queryParam("term");
            var courses = CourseRepository.getEntities();
            List<Course> filteredCourses;

            if (term != null) {
                filteredCourses = courses.stream()
                    .filter(c -> c.getName().toLowerCase().contains(term.toLowerCase())
                        || c.getDescription().toLowerCase().contains(term.toLowerCase()))
                    .toList();
            } else {
                filteredCourses = List.copyOf(courses);
            }

            var page = new CoursesPage(filteredCourses, term);
            ctx.render("courses/index.jte", model("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var courseId = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.find(courseId).orElseThrow(NotFoundResponse::new);
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.start(7070);
    }
}
