package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.util.NamedRoutes;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UsersController {
    public static void index(Context ctx) throws SQLException {
        var term = ctx.queryParam("term");
        var users = UserRepository.getEntities();
        List<User> filteredUsers;

        if (term != null) {
            filteredUsers = users.stream()
                .filter(c -> c.getName().toLowerCase().contains(term.toLowerCase())
                    || c.getEmail().toLowerCase().contains(term.toLowerCase()))
                .toList();
        } else {
            filteredUsers = List.copyOf(users);
        }

        var page = new UsersPage(filteredUsers, term);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        page.setFlashType(ctx.consumeSessionAttribute("flash-type"));
        ctx.render("users/index.jte", model("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id).orElseThrow(NotFoundResponse::new);
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildUserPage();
        ctx.render("users/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        var name = ctx.formParamAsClass("name", String.class).get().trim();
        var email = ctx.formParamAsClass("email", String.class).get().trim().toLowerCase();

        try {
            var passwordConfirmation = ctx.formParam("passwordConfirmation");
            var password = ctx.formParamAsClass("password", String.class)
                .check(value -> Objects.equals(value, passwordConfirmation),
                    "Passwords don't match")
                .get();
            UserRepository.save(new User(name, email, password));

            ctx.sessionAttribute("flash", "User successfully created");
            ctx.sessionAttribute("flash-type", "alert alert-success");
            ctx.redirect(NamedRoutes.usersPath());

        } catch (ValidationException e) {
            var page = new BuildUserPage(name, email, e.getErrors());

            page.setFlash("Failed to create user");
            page.setFlashType("alert alert-danger");
            ctx.render("users/build.jte", model("page", page));
        }
    }
}
