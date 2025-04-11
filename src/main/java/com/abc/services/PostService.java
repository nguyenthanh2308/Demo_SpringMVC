package com.abc.services;

import java.util.List;

import com.abc.entities.Post;

public interface PostService {
    List<Post> getAllPost(int id);
    List<Post> getPostById(int id);
    boolean createdPost(Post post);
}