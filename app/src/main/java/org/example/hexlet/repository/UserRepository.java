package org.example.hexlet.repository;

import org.example.hexlet.model.User;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository extends BaseRepository {

    public static void save(User user) throws SQLException {
        var sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();

            var keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getLong(1));
            } else {
                throw new SQLException("DB has not returned an id after saving the entity");
            }
        }
    }

    public static List<User> searchByName(String term) throws SQLException {
        var sql = "SELECT * FROM users WHERE name ILIKE '?%'";
        try (
            var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, term);

            var rs = stmt.executeQuery();
            var result = new ArrayList<User>();
            while (rs.next()) {
                var id = rs.getLong("id");
                var name = rs.getString("name");
                var email = rs.getString("email");
                var password = rs.getString("password");
                var user = new User(name, email, password);
                user.setId(id);
                result.add(user);
            }
            return result;
        }
    }

    public static Optional<User> find(Long id) throws SQLException {
        var sql = "SELECT * FROM users WHERE id = ?";
        try (
            var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)
        ) {
            stmt.setLong(1, id);

            var rs = stmt.executeQuery();
            if (rs.next()) {
                var name = rs.getString("name");
                var email = rs.getString("email");
                var password = rs.getString("password");
                var user = new User(name, email, password);
                user.setId(id);
                return Optional.of(user);
            } else {
                return Optional.empty();
            }
        }
    }

    public static List<User> getEntities() throws SQLException {
        var sql = "SELECT * FROM users";
        try (
            var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(sql)
        ) {
            var rs = stmt.executeQuery();
            var result = new ArrayList<User>();
            while (rs.next()) {
                var id = rs.getLong("id");
                var name = rs.getString("name");
                var email = rs.getString("email");
                var password = rs.getString("password");
                var user = new User(name, email, password);
                user.setId(id);
                result.add(user);
            }
            return result;
        }
    }

    public static void fillWithFakes() throws SQLException {
        var sam = new User("Sam", "ogogo@outlook.com", "123");
        var tom = new User("Tom", "go@gmail.com", "213");
        var pam = new User("Pam", "aga@mail.ru", "312");

        save(sam);
        save(tom);
        save(pam);
    }

    public static boolean nameExists(String name) throws SQLException {
        var sql = "SELECT id FROM users WHERE name = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, name);
            var rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public static boolean emailExists(String email) throws SQLException {
        var sql = "SELECT id FROM users WHERE email = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, email);
            var rs = stmt.executeQuery();
            return rs.next();
        }
    }
}