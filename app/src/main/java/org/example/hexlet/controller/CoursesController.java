package org.example.hexlet.controller;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.util.NamedRoutes;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.repository.CourseRepository;

import java.sql.SQLException;
import java.util.List;

import static io.javalin.rendering.template.TemplateUtil.model;

public class CoursesController {
    public static void index(Context ctx) throws SQLException {
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
    }

    public static void show(Context ctx) throws SQLException {
        var courseId = ctx.pathParamAsClass("id", Long.class).get();
        var course = CourseRepository.find(courseId).orElseThrow(NotFoundResponse::new);
        var page = new CoursePage(course);
        ctx.render("courses/show.jte", model("page", page));
    }

    public static void build(Context ctx) {
        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", model("page", page));
    }

    public static void create(Context ctx) throws SQLException {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                .check(value -> value.length() >= 2,
                    "Name must be at least 2 characters")
                .get();
            var description = ctx.formParamAsClass("description", String.class)
                .check(value -> value.length() >= 10,
                    "Description must be at least 10 characters")
                .get();
            CourseRepository.save(new Course(name, description));
            ctx.redirect(NamedRoutes.coursesPath());
        } catch (ValidationException e) {
            var page = new BuildCoursePage(
                ctx.formParam("name"),
                ctx.formParam("description"),
                e.getErrors()
            );
            ctx.render("courses/build.jte", model("page", page));
        }
    }
}
