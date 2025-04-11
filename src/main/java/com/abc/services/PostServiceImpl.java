package com.abc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.dao.PostDAO;
import com.abc.entities.Post;

@Service // Spring sẽ quản lý bean này
public class PostServiceImpl implements PostService {

	@Autowired
    private PostDAO postDAO;

    @Override
    public List<Post> getAllPost(int id) {
        return postDAO.getALLPost(id);
    }

    @Override
    public List<Post> getPostById(int id) {
        return postDAO.getPostById(id);
    }

    @Override
    public boolean createdPost(Post post) {
        return postDAO.createdPost(post);
    }
}