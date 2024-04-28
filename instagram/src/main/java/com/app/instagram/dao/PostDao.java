package com.app.instagram.dao;

import com.app.instagram.entity.Post;

public interface PostDao {

    String COLLECTION_NAME = "posts";

    Post save(Post post);

    Post findById(String postId);
}
