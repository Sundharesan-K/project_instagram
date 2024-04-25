package com.app.instagram.dao;

import com.app.instagram.entity.Post;

public interface PostDao {
    Post save(Post post);

    Post findById(String postId);
}
