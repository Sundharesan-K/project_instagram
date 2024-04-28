package com.app.instagram.dao.impl;

import com.app.instagram.dao.LikeDao;
import com.app.instagram.entity.LikeAndDisLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeDaoImpl implements LikeDao {

    private final MongoTemplate mongoTemplate;

    @Override
    public LikeAndDisLike findByPostIdAndUsername(String postId, String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("postId").is(postId)
            .and("username")
            .is(username));
        return mongoTemplate.findOne(query, LikeAndDisLike.class);
    }

    @Override
    public void save(LikeAndDisLike likeAndDisLike) {
        mongoTemplate.save(likeAndDisLike);
    }
}
