package com.app.instagram.dao.impl;

import com.app.instagram.dao.PostDao;
import com.app.instagram.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostDaoImpl implements PostDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public Post save(Post post) {
        return mongoTemplate.save(post);
    }

    @Override
    public Post findById(String postId) {
        return mongoTemplate.findById(postId, Post.class);
    }
}
