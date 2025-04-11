package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.abc.config.DatabaseConfig;
import com.abc.entities.User;

@Repository
public class UserDAO {

    public User getUserByUserName(String userName) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("created_at")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users (username, password, created_at) VALUES (?, ?, NOW())";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassWord());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Thêm phương thức tìm kiếm user theo số following và follower
    public List<User> searchUsersByFollowCount(int minFollowing, int minFollowers) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.* FROM users u " +
                    "WHERE (SELECT COUNT(*) FROM follows WHERE following_user_id = u.id) >= ? " +
                    "AND (SELECT COUNT(*) FROM follows WHERE followed_user_id = u.id) >= ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, minFollowing);
            stmt.setInt(2, minFollowers);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}