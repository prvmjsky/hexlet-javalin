package org.example.hexlet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.example.hexlet.controller.SessionsController;
import org.example.hexlet.dto.MainPage;
import org.example.hexlet.repository.BaseRepository;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.Instant;
import java.util.stream.Collectors;

import static io.javalin.rendering.template.TemplateUtil.model;

public class App {

    public static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static String getDatabaseUrl() {
        return System.getenv().getOrDefault("JDBC_DATABASE_URL",
            "jdbc:h2:mem:hexlet_project;DB_CLOSE_DELAY=-1;");
    }

    public static Javalin getApp() throws SQLException {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDatabaseUrl());

        var dataSource = new HikariDataSource(hikariConfig);

        var url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
            .lines().collect(Collectors.joining("\n"));

        try (
            var connection = dataSource.getConnection();
            var statement = connection.createStatement()
        ) {
            statement.execute(sql);
        }

        BaseRepository.dataSource = dataSource;

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        return app;
    }

    public static void main(String[] args) throws SQLException {
        var app = getApp();
        app.before(ctx -> LOG.info(Instant.now().toString()));

        UserRepository.fillWithFakes();
        CourseRepository.fillWithFakes();
        PostRepository.fillWithFakes();

        app.get(NamedRoutes.buildSessionPath(), SessionsController::build);
        app.post(NamedRoutes.sessionsPath(), SessionsController::create);
        app.delete(NamedRoutes.sessionsPath(), SessionsController::destroy);

        app.get(NamedRoutes.rootPath(), ctx -> {
            var visited = Boolean.valueOf(ctx.cookie("visited"));
            String currentUser = ctx.sessionAttribute("currentUser");
            var page = new MainPage(visited, currentUser);
            ctx.render("index.jte", model("page", page));
            ctx.cookie("visited", String.valueOf(true));
        });

        app.get(NamedRoutes.welcomingPath(), ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result(String.format("Hello, %s!", name));
        });

        app.get(NamedRoutes.usersPath(), UsersController::index);
        app.get(NamedRoutes.buildUserPath(), UsersController::build);
        app.post(NamedRoutes.usersPath(), UsersController::create);
        app.get(NamedRoutes.userPath("{id}"), UsersController::show);

        app.get(NamedRoutes.coursesPath(), CoursesController::index);
        app.get(NamedRoutes.buildCoursePath(), CoursesController::build);
        app.post(NamedRoutes.coursesPath(), CoursesController::create);
        app.get(NamedRoutes.coursePath("{id}"), CoursesController::show);

        app.get(NamedRoutes.userPostPath("{id}", "{postId}"), ctx -> {
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
