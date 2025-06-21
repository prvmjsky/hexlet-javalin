package org.example.hexlet.repository;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.Course;

public class CourseRepository extends BaseRepository {

    public static void save(Course course) throws SQLException {
        var sql = "INSERT INTO courses (name, description) VALUES (?, ?)";
        try (
            var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getDescription());
            stmt.executeUpdate();

            var keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                course.setId(keys.getLong(1));
            } else {
                throw new SQLException("DB has not returned an id after saving the entity");
            }
        }
    }

    public static List<Course> searchByName(String term) throws SQLException {
        var sql = "SELECT * FROM courses WHERE name ILIKE '?%'";
        try (
            var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, term);

            var rs = stmt.executeQuery();
            var result = new ArrayList<Course>();
            while (rs.next()) {
                var id = rs.getLong("id");
                var name = rs.getString("name");
                var description = rs.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                result.add(course);
            }
            return result;
        }
    }

    public static Optional<Course> find(Long id) throws SQLException {
        var sql = "SELECT * FROM courses WHERE id = ?";
        try (
            var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                var name = rs.getString("name");
                var description = rs.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                return Optional.of(course);
            } else {
                return Optional.empty();
            }
        }
    }

    public static List<Course> getEntities() throws SQLException {
        var sql = "SELECT * FROM courses";
        try (
            var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)
        ) {
            var rs = stmt.executeQuery();
            var result = new ArrayList<Course>();
            while (rs.next()) {
                var id = rs.getLong("id");
                var name = rs.getString("name");
                var description = rs.getString("description");
                var course = new Course(name, description);
                course.setId(id);
                result.add(course);
            }
            return result;
        }
    }

    public static void fillWithFakes() throws SQLException {
        var course1 = new Course("Java", "A damn fine cup of coffee");
        var course2 = new Course("Python", "A long one");
        var course3 = new Course("PHP", "ZXC");

        save(course1);
        save(course2);
        save(course3);
    }
}