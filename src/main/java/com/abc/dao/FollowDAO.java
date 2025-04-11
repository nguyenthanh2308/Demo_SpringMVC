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
public class FollowDAO {

    public List<User> getFollowerUser(int id) {
        List<User> userFollower = new ArrayList<>();
        String sql = "SELECT * FROM users "
                   + "JOIN follows ON users.id = follows.following_user_id "
                   + "WHERE follows.followed_user_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userFollower.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFollower;
    }

    public List<User> getFollowedUsers(int id) {
        List<User> userFollowed = new ArrayList<>();
        String sql = "SELECT * FROM users "
                   + "JOIN follows ON users.id = follows.followed_user_id "
                   + "WHERE follows.following_user_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                userFollowed.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("created_at")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFollowed;
    }

    public void followUser(int followingUserId, int followedUserId) {
        String sql = "INSERT INTO follows (following_user_id, followed_user_id, created_at) VALUES (?, ?, NOW())";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, followingUserId);
            stmt.setInt(2, followedUserId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unfollowUser(int followingUserId, int followedUserId) {
        String sql = "DELETE FROM follows WHERE following_user_id = ? AND followed_user_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, followingUserId);
            stmt.setInt(2, followedUserId);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getSuggestedFollows(int userId) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT u.* FROM users u "
                   + "LEFT JOIN follows f ON u.id = f.followed_user_id AND f.following_user_id = ? "
                   + "WHERE f.followed_user_id IS NULL AND u.id <> ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("created_at")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Thêm phương thức đếm số following của một user
    public int countFollowing(int userId) {
        String sql = "SELECT COUNT(*) FROM follows WHERE following_user_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Thêm phương thức đếm số follower của một user
    public int countFollowers(int userId) {
        String sql = "SELECT COUNT(*) FROM follows WHERE followed_user_id = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}