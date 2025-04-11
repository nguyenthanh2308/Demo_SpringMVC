package com.abc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.abc.config.DatabaseConfig;
import com.abc.entities.Post;

@Repository
public class PostDAO {

    public List<Post> getALLPost(int id){
        List<Post> posts = new ArrayList<>();

        String sql = "SELECT DISTINCT posts.*, users.username " +
                     "FROM posts " +
                     "JOIN users ON posts.user_id = users.id " +
                     "LEFT JOIN follows ON posts.user_id = follows.followed_user_id " +
                     "WHERE follows.following_user_id = ? OR posts.user_id = ? " +
                     "ORDER BY posts.created_at DESC";

        try (Connection conn = DatabaseConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.setInt(2, id); 
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                posts.add(new Post(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("body"),
                    rs.getInt("user_id"),
                    rs.getString("status"),
                    rs.getString("created_at")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    public List<Post> getPostById(int id){
        List<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM posts WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DatabaseConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                posts.add(new Post(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("body"),
                    rs.getInt("user_id"),
                    rs.getString("status"),
                    rs.getString("created_at")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }


    public boolean createdPost(Post post) {
        String sql = "INSERT INTO posts (title, body, user_id, status, created_at) VALUES (?, ?, ?, ?, NOW())";

        try (Connection conn = DatabaseConfig.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, post.getTitle());
            stmt.setString(2, post.getBody());
            stmt.setInt(3, post.getUserId());
            stmt.setString(4, post.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
